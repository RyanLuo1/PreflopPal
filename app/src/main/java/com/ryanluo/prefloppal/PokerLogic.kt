package com.ryanluo.prefloppal

import kotlin.math.abs

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
            "sb" to "SB", "smallblind" to "SB",
            "bb" to "BB", "bigblind" to "BB"
        )
        val actionKeywords = mapOf(
            "folds" to "folds", "fold" to "folds", "folded" to "folds",
            "raises" to "raises", "raise" to "raises", "raised" to "raises",
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
                    explanation = "This hand ($handKey) is in the RFI range for $position. You should raise."
                } else {
                    advice = "Fold"
                    explanation = "This hand ($handKey) is not in the RFI range for $position. You should fold."
                }
            }
            lastRaise?.second == "3-bets" -> {
                val threeBetPosition = lastRaise.first
                if (position in FacingThreeBetRanges.RANGES && threeBetPosition in FacingThreeBetRanges.RANGES[position].orEmpty()) {
                    val facingThreeBetRange = FacingThreeBetRanges.RANGES[position]!![threeBetPosition]!!
                    when {
                        facingThreeBetRange.fourBetValueRange.contains(hand) -> {
                            advice = "4-Bet for Value"
                            explanation = "This hand ($handKey) is strong enough to 4-bet for value against a 3-bet from $threeBetPosition when you're in $position."
                        }
                        facingThreeBetRange.fourBetBluffRange.contains(hand) -> {
                            advice = "4-Bet as a Bluff or Fold"
                            explanation = "This hand ($handKey) is suitable for a 4-bet bluff against a 3-bet from $threeBetPosition when you're in $position."
                        }
                        facingThreeBetRange.callRange.contains(hand) -> {
                            advice = "Call"
                            explanation = "This hand ($handKey) is in the cold calling range against a 3-bet from $threeBetPosition when you're in $position."
                        }
                        else -> {
                            advice = "Fold"
                            explanation = "This hand ($handKey) is not strong enough to continue against a 3-bet from $threeBetPosition when you're in $position."
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
                            explanation = "This hand ($handKey) is in the value 3-betting range against a raise from $raiserPosition when you're in $position."
                        }
                        threeBetRange.bluffRange.contains(hand) -> {
                            advice = "3-Bet as a Bluff or Fold"
                            explanation = "This hand ($handKey) is in the bluff 3-betting range against a raise from $raiserPosition when you're in $position."
                        }
                        RFICallingRanges.RANGES[position]?.get(raiserPosition)?.contains(hand) == true -> {
                            advice = "Call"
                            explanation = "This hand ($handKey) is in the calling range against a raise from $raiserPosition when you're in $position."
                        }
                        else -> {
                            advice = "Fold"
                            explanation = "This hand ($handKey) is not in the calling or 3-betting range against a raise from $raiserPosition when you're in $position."
                        }
                    }
                } else {
                    advice = "Fold"
                    explanation = "There's no defined strategy for this position ($position) against a raise from $raiserPosition. It's safest to fold."
                }
            }
            else -> {
                advice = "Fold"
                explanation = "Unexpected scenario. For safety, it's recommended to fold unless you're very confident. Consider the actions: ${actions.joinToString(", ") { "${it.first} ${it.second}" }}."
            }
        }

        return Triple(advice, explanation, handStrength)
    }

    private fun calculateHandStrength(hand: Hand, position: String): Double {
        val rankOrder = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')

        fun calculateBaseStrength(hand: Hand): Double {
            return when {
                hand.isPair -> 6.0 + (14 - rankOrder.indexOf(hand.card1.rank)) * 0.3
                else -> {
                    val highCardRank = minOf(rankOrder.indexOf(hand.card1.rank), rankOrder.indexOf(hand.card2.rank))
                    val lowCardRank = maxOf(rankOrder.indexOf(hand.card1.rank), rankOrder.indexOf(hand.card2.rank))
                    5.0 + (14 - highCardRank) * 0.2 + (14 - lowCardRank) * 0.1
                }
            }
        }

        fun getPositionMultiplier(position: String): Double {
            return when (position) {
                "BTN" -> 1.2
                "CO" -> 1.15
                "MP" -> 1.1
                "UTG" -> 1.0
                "SB" -> 0.95
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
                gap == 1 -> 0.5  // Connector
                gap == 2 -> 0.3  // One-gapper
                gap == 3 -> 0.1  // Two-gapper
                else -> 0.0
            }
        }

        val baseStrength = calculateBaseStrength(hand)
        val positionMultiplier = getPositionMultiplier(position)
        val suitednessBonus = if (hand.isSuited) 0.5 else 0.0
        val connectorBonus = getConnectorBonus(hand)
        return (baseStrength * positionMultiplier + suitednessBonus + connectorBonus).coerceIn(0.0, 10.0)
    }
}