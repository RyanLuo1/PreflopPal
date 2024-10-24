package com.ryanluo.prefloppal

import kotlin.math.abs
import kotlin.math.roundToInt

data class Card(val rank: Char, val suit: Char) {
    override fun toString() = "$rank$suit"
}

data class Hand(val card1: Card, val card2: Card) {
    val isSuited = card1.suit == card2.suit
    val isPair = card1.rank == card2.rank

    private val rankOrder = "AKQJT98765432"

    fun toKey(): String {
        val (highCard, lowCard) = if (rankOrder.indexOf(card1.rank) <= rankOrder.indexOf(card2.rank)) {
            card1 to card2
        } else {
            card2 to card1
        }

        return when {
            isPair -> "${highCard.rank}${lowCard.rank}"
            isSuited -> "${highCard.rank}${lowCard.rank}s"
            else -> "${highCard.rank}${lowCard.rank}o"
        }
    }
}

class Range(private val hands: Set<String>) {
    fun contains(hand: Hand): Boolean {
        return hands.contains(hand.toKey())
    }
}

object PokerLogic {
    private val POSITIONS = listOf("UTG", "MP", "CO", "BTN", "SB", "BB")

    private val RFI_RANGES = RFI_Ranges.RFI_RANGES
    private val CALLING_RANGES = RFICallingRanges.RANGES
    private val THREE_BET_RANGES = RFIThreeBetRanges.RANGES

    private fun parsePreviousAction(previousAction: String): List<Pair<String, String>> {
        if (previousAction.isBlank() || previousAction.toLowerCase() == "no action") {
            return emptyList()
        }

        val actions = previousAction.split(Regex("[,.]")).map { it.trim().toLowerCase() }
        val positionMap = mapOf(
            "utg" to "UTG", "utg+1" to "UTG", "utg+2" to "UTG",
            "mp" to "MP", "mp+1" to "MP", "hj" to "MP",
            "co" to "CO", "cutoff" to "CO",
            "btn" to "BTN", "button" to "BTN",
            "sb" to "SB", "small blind" to "SB",
            "bb" to "BB", "big blind" to "BB"
        )
        val actionKeywords = mapOf(
            "folds" to "folds", "fold" to "folds", "folded" to "folds",
            "raises" to "raises", "raise" to "raises", "raised" to "raises", "opens" to "raises", "open" to "raises",
            "3 bets" to "3-bets", "3bet" to "3-bets", "3-bet" to "3-bets", "3 bet" to "3-bets", "3!" to "3-bets"
        )

        val parsedActions = mutableListOf<Pair<String, String>>()
        var raiseCount = 0

        for (action in actions) {
            val words = action.split(Regex("\\s+"))
            var position: String? = null
            var actionType: String? = null

            for (word in words) {
                if (position == null) {
                    position = positionMap[word.toLowerCase()]
                }
                if (actionType == null) {
                    actionType = actionKeywords[word.toLowerCase()]
                    if (actionType == null && word.toLowerCase().contains("bet")) {
                        actionType = "3-bets"
                    }
                }
                if (position != null && actionType != null) {
                    break
                }
            }

            if (position != null && actionType != null) {
                if (actionType == "raises") {
                    raiseCount++
                    if (raiseCount > 1) {
                        actionType = "3-bets"
                    }
                }
                parsedActions.add(Pair(position, actionType))
            }
        }

        return parsedActions
    }

    fun getAdvice(hand: Hand, position: String, previousAction: String?): Triple<String, String, Double> {
        val handKey = hand.toKey()
        val handStrength = calculateHandStrength(hand, position)

        val actions = parsePreviousAction(previousAction ?: "")
        val lastRaise = actions.lastOrNull { it.second == "raises" || it.second == "3-bets" }

        val advice: String
        val explanation: String

        when {
            actions.isEmpty() || previousAction.isNullOrBlank() || previousAction == "No action" || actions.all { it.second == "folds" } -> {
                // RFI situation
                if (position in RFI_RANGES && RFI_RANGES[position]?.contains(hand) == true) {
                    advice = "Raise"
                    explanation = "Your hand ($handKey) is strong enough to raise from $position. It’s a good spot to be more aggressive."
                } else {
                    advice = "Fold"
                    explanation = "Your hand ($handKey) isn’t ideal for raising from $position. It’s better to fold and wait for a stronger spot."
                }
            }
            lastRaise?.second == "3-bets" -> {
                val threeBetPosition = lastRaise.first
                if (position in FacingThreeBetRanges.RANGES && threeBetPosition in FacingThreeBetRanges.RANGES[position].orEmpty()) {
                    val facingThreeBetRange = FacingThreeBetRanges.RANGES[position]!![threeBetPosition]!!
                    when {
                        facingThreeBetRange.fourBetValueRange.contains(hand) -> {
                            advice = "4-Bet for Value"
                            explanation = "This hand ($handKey) is strong enough to 4-bet for value against a 3-bet from $threeBetPosition when you're in $position. " +
                                    "A value 4-bet aims to maximize the potential of your strong hand by building a larger pot, especially when you expect weaker hands to continue or call."
                        }
                        facingThreeBetRange.fourBetBluffRange.contains(hand) -> {
                            advice = "4-Bet as a Bluff or Fold"
                            explanation = "This hand ($handKey) can be used as a 4-bet bluff against a 3-bet from $threeBetPosition when you're in $position. " +
                                    "Bluffing with a 4-bet adds pressure on your opponent, often forcing folds from marginal hands. Using these bluffs also helps balance your overall range, " +
                                    "making it harder for opponents to read your hand strength in similar situations."
                        }
                        facingThreeBetRange.callRange.contains(hand) -> {
                            advice = "Call"
                            explanation = "This hand ($handKey) is solid enough to call against a 3-bet from $threeBetPosition when you're in $position. " +
                                    "It has the potential to play well post-flop, allowing you to see how the board develops without committing more chips preflop."
                        }
                        else -> {
                            advice = "Fold"
                            explanation = "This hand ($handKey) isn't strong enough to continue against a 3-bet from $threeBetPosition when you're in $position. " +
                                    "Folding now helps avoid risky situations with weaker hands."
                        }
                    }
                } else {
                    advice = "Fold"
                    explanation = "There's no defined strategy for this position ($position) against a 3-bet from $threeBetPosition. It's safest to fold."
                }
            }
            lastRaise?.second == "raises" -> {
                val raiserPosition = lastRaise.first
                if (position in RFIThreeBetRanges.RANGES && raiserPosition in RFIThreeBetRanges.RANGES[position].orEmpty()) {
                    val threeBetRange = RFIThreeBetRanges.RANGES[position]!![raiserPosition]!!
                    when {
                        threeBetRange.valueRange.contains(hand) -> {
                            advice = "3-Bet for Value"
                            explanation = "This hand ($handKey) is strong enough to 3-bet for value against a raise from $raiserPosition when you're in $position. " +
                                    "A value 3-bet here aims to get more chips into the pot with a hand that performs well postflop."
                        }
                        threeBetRange.bluffRange.contains(hand) -> {
                            advice = "3-Bet as a Bluff or Fold"
                            explanation = "This hand ($handKey) is in the 3-bet bluffing range against a raise from $raiserPosition when you're in $position. " +
                                    "3-bet bluffs add pressure on your opponent and also balance your own 3-betting range, but they should only be used a small percentage of the time."
                        }
                        RFICallingRanges.RANGES[position]?.get(raiserPosition)?.contains(hand) == true -> {
                            advice = "Call"
                            explanation = "This hand ($handKey) is in the calling range against a raise from $raiserPosition when you're in $position."
                        }
                        else -> {
                            advice = "Fold"
                            explanation = "This hand ($handKey) is not strong enough to call or 3-bet against a raise from $raiserPosition when you're in $position. " +
                                    "Folding is the safest option here."
                        }
                    }
                } else {
                    advice = "Fold"
                    explanation = "There's no defined strategy for this position ($position) against a raise from $raiserPosition. It's safest to fold."
                }
            }
            else -> {
                advice = "Fold"
                explanation = "Unexpected scenario. It's recommended to fold unless you're very confident. Consider the actions: ${actions.joinToString(", ") { "${it.first} ${it.second}" }}."
            }
        }

        return Triple(advice, explanation, handStrength)
    }

    private fun calculateHandStrength(hand: Hand, position: String): Double {
        val rankOrder = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')

        fun calculateBaseStrength(hand: Hand): Double {
            return when {
                // High pairs get high base scores
                hand.isPair -> 6.0 + (14 - rankOrder.indexOf(hand.card1.rank)) * 0.3
                // Non-pairs are rated by their card ranks
                else -> {
                    val highCardRank = minOf(rankOrder.indexOf(hand.card1.rank), rankOrder.indexOf(hand.card2.rank))
                    val lowCardRank = maxOf(rankOrder.indexOf(hand.card1.rank), rankOrder.indexOf(hand.card2.rank))
                    4.5 + (14 - highCardRank) * 0.2 + (14 - lowCardRank) * 0.1
                }
            }
        }

        fun getPositionMultiplier(position: String): Double {
            return when (position) {
                "BTN" -> 1.1
                "CO" -> 1.05
                "MP" -> 1.0
                "UTG" -> 0.95
                "SB" -> 0.85
                "BB" -> 0.9
                else -> 1.0
            }
        }

        fun getConnectorBonus(hand: Hand): Double {
            if (hand.isPair) return 0.0
            val rank1 = rankOrder.indexOf(hand.card1.rank)
            val rank2 = rankOrder.indexOf(hand.card2.rank)
            val gap = abs(rank1 - rank2)
            return when {
                gap == 1 -> 0.4  // Connector
                gap == 2 -> 0.2  // One-gapper
                gap == 3 -> 0.1  // Two-gapper
                else -> 0.0
            }
        }

        fun getBroadwayBonus(hand: Hand): Double {
            val isBroadway1 = hand.card1.rank in listOf('A', 'K', 'Q', 'J', 'T')
            val isBroadway2 = hand.card2.rank in listOf('A', 'K', 'Q', 'J', 'T')
            return when {
                isBroadway1 && isBroadway2 -> 0.4
                isBroadway1 || isBroadway2 -> 0.1
                else -> 0.0
            }
        }

        fun getSuitednessBonus(hand: Hand): Double {
            // If the hand is suited and one of the cards is an Ace, apply a bonus of 1.0
            return when {
                hand.isSuited && (hand.card1.rank == 'A' || hand.card2.rank == 'A') -> 1.4
                hand.isSuited -> 0.5
                else -> 0.0
            }
        }

        fun getLowCardPenalty(hand: Hand): Double {
            val rank1 = rankOrder.indexOf(hand.card1.rank)
            val rank2 = rankOrder.indexOf(hand.card2.rank)

            // Check if one of the cards is an Ace
            val hasAce = hand.card1.rank == 'A' || hand.card2.rank == 'A'

            // Calculate the base penalty for low cards
            val basePenalty = when {
                rank1 >= 8 && rank2 >= 8 -> 2.0  // Heavy penalty for very low cards like 7-2
                rank1 >= 6 && rank2 >= 6 -> 1.5  // Moderate penalty for hands like 8-5
                rank1 >= 4 && rank2 >= 4 -> 1.0  // Smaller penalty for hands like 6-4
                else -> 0.0
            }

            // If there's an Ace, halve the penalty
            return if (hasAce) basePenalty / 2 else basePenalty
        }

        fun getDisconnectedPenalty(hand: Hand): Double {
            if (hand.isPair) return 0.0
            val rank1 = rankOrder.indexOf(hand.card1.rank)
            val rank2 = rankOrder.indexOf(hand.card2.rank)
            val gap = abs(rank1 - rank2)

            // Apply a penalty starting at 1.5 for a gap of 4, with an additional 0.3 per rank beyond that.
            return if (gap >= 4) 1.5 + (gap - 4) * 0.3 else 0.0
        }

        val baseStrength = calculateBaseStrength(hand)
        val positionMultiplier = getPositionMultiplier(position)
        val suitednessBonus = getSuitednessBonus(hand)
        val connectorBonus = getConnectorBonus(hand)
        val broadwayBonus = getBroadwayBonus(hand)
        val lowCardPenalty = getLowCardPenalty(hand)
        val disconnectedPenalty = getDisconnectedPenalty(hand)

        // Adjust the raw strength based on bonuses and penalties
        val rawStrength = (baseStrength * positionMultiplier + suitednessBonus + connectorBonus + broadwayBonus - lowCardPenalty - disconnectedPenalty).coerceIn(0.0, 10.0)
        return (rawStrength * 10).roundToInt() / 10.0
    }

}