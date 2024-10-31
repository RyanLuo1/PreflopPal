package com.ryanluo.prefloppal.utils

import com.ryanluo.prefloppal.models.Nine_Max_RFICallingRanges
import com.ryanluo.prefloppal.models.Nine_Max_FacingThreeBetRanges
import com.ryanluo.prefloppal.models.Nine_Max_RFIThreeBetRanges
import com.ryanluo.prefloppal.models.Nine_Max_RFI_Ranges

import com.ryanluo.prefloppal.models.Six_Max_FacingThreeBetRanges
import com.ryanluo.prefloppal.models.Six_Max_RFICallingRanges
import com.ryanluo.prefloppal.models.Six_Max_RFIThreeBetRanges
import com.ryanluo.prefloppal.models.Six_Max_RFI_Ranges

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

enum class TableSize {
    SIX_MAX,
    NINE_MAX
}

object PokerLogic {
    private val SIX_MAX_POSITIONS = listOf("UTG", "MP", "CO", "BTN", "SB", "BB")
    private val NINE_MAX_POSITIONS = listOf("UTG", "UTG+1", "UTG+2", "MP", "LJ", "HJ", "CO", "BTN", "SB", "BB")

    // 6max ranges (your existing ones)
    private val SIX_MAX_RFI_RANGES = Six_Max_RFI_Ranges.RANGES
    private val SIX_MAX_CALLING_RANGES = Six_Max_RFICallingRanges.RANGES
    private val SIX_MAX_THREE_BET_RANGES = Six_Max_RFIThreeBetRanges.RANGES
    private val SIX_MAX_FACING_THREE_BET_RANGES = Nine_Max_FacingThreeBetRanges.RANGES

    // 9max ranges (placeholders - you'll implement these)
    private val NINE_MAX_RFI_RANGES = Nine_Max_RFI_Ranges.RANGES
    private val NINE_MAX_CALLING_RANGES = Nine_Max_RFICallingRanges.RANGES
    private val NINE_MAX_THREE_BET_RANGES = Nine_Max_RFIThreeBetRanges.RANGES
    private val NINE_MAX_FACING_THREE_BET_RANGES = Nine_Max_FacingThreeBetRanges.RANGES


    private fun parsePreviousAction(previousAction: String, tableSize: TableSize): List<Pair<String, String>> {
        if (previousAction.isBlank() || previousAction.toLowerCase() == "no action") {
            return emptyList()
        }

        // Split on multiple delimiters including "then", commas, and periods
        val actions = previousAction
            .replace(" then ", ",")  // Replace "then" with comma
            .replace(" and ", ",")   // Replace "and" with comma
            .replace(";", ",")       // Replace semicolon with comma
            .split(Regex("[,.]"))
            .map { it.trim().toLowerCase() }

        val positionPhrases = listOf(
            "under the gun" to "UTG",
            "utg plus one" to "UTG+1",
            "utg plus two" to "UTG+2",
            "middle position" to "MP",
            "low jack" to "LJ",
            "high jack" to "HJ",
            "cut off" to "CO",
            "small blind" to "SB",
            "big blind" to "BB"
        )

        val positionMap = when (tableSize) {
            TableSize.NINE_MAX -> mapOf(
                "utg" to "UTG",
                "underthegun" to "UTG",
                "utg+1" to "UTG+1",
                "utgplusone" to "UTG+1",
                "utg+2" to "UTG+2",
                "utgplustwo" to "UTG+2",
                "mp" to "LJ",
                "lj" to "LJ",
                "lowjack" to "LJ",
                "hj" to "HJ",
                "highjack" to "HJ",
                "co" to "CO",
                "cutoff" to "CO",
                "btn" to "BTN",
                "button" to "BTN",
                "sb" to "SB",
                "smallblind" to "SB",
                "bb" to "BB",
                "bigblind" to "BB"
            )
            TableSize.SIX_MAX -> mapOf(
                "utg" to "UTG",
                "underthegun" to "UTG",
                "utg+1" to "UTG",
                "utgplusone" to "UTG",
                "utg+2" to "UTG",
                "utgplustwo" to "UTG",
                "mp" to "MP",
                "middleposition" to "MP",
                "lj" to "MP",
                "lowjack" to "MP",
                "hj" to "MP",
                "highjack" to "MP",
                "co" to "CO",
                "cutoff" to "CO",
                "btn" to "BTN",
                "button" to "BTN",
                "sb" to "SB",
                "smallblind" to "SB",
                "bb" to "BB",
                "bigblind" to "BB"
            )
        }

        val actionKeywords = mapOf(
            // Fold variations
            "folds" to "folds",
            "fold" to "folds",
            "folded" to "folds",
            "mucks" to "folds",
            "passes" to "folds",

            // Raise variations
            "raises" to "raises",
            "raise" to "raises",
            "raised" to "raises",
            "opens" to "raises",
            "open" to "raises",
            "bets" to "raises",
            "bet" to "raises",
            "rfi" to "raises",

            // 3-bet variations
            "3 bets" to "3-bets",
            "3bet" to "3-bets",
            "3-bet" to "3-bets",
            "3 bet" to "3-bets",
            "3!" to "3-bets",
            "3b" to "3-bets",
            "3!" to "3-bets",
            "3betting" to "3-bets",
            "3-betting" to "3-bets",
            "reraised" to "3-bets",
            "reraise" to "3-bets",
            "reraises" to "3-bets",

            // 4-bet variations
            "4 bets" to "4-bets",
            "4bet" to "4-bets",
            "4-bet" to "4-bets",
            "4 bet" to "4-bets",
            "4!" to "4-bets",
            "4b" to "4-bets",
            "4betting" to "4-bets",
            "4-betting" to "4-bets",
        )

        val parsedActions = mutableListOf<Pair<String, String>>()
        var raiseCount = 0

        for (action in actions) {
            val words = action.split(Regex("\\s+"))
            var position: String? = null
            var actionType: String? = null

            // Try to match position first
            val fullText = words.joinToString(" ").toLowerCase()

            // First try matching full phrases
            for ((phrase, pos) in positionPhrases) {
                if (fullText.contains(phrase)) {
                    position = pos
                    break
                }
            }

            // If no phrase match, try single word matches
            if (position == null) {
                val joinedWords = words.joinToString("").toLowerCase()
                position = positionMap[joinedWords] ?: positionMap[fullText] ?: words
                    .asSequence()
                    .map { it.toLowerCase().replace(" ", "") }
                    .firstNotNullOfOrNull { positionMap[it] }
            }

            // Look for action type
            for (word in words) {
                val lowercaseWord = word.toLowerCase()
                if (actionType == null) {
                    actionType = actionKeywords[lowercaseWord]
                }
            }

            if (position != null && actionType != null) {
                if (actionType == "raises") {
                    raiseCount++
                    if (raiseCount > 1) {
                        actionType = "3-bets"
                    }
                }
                if (actionType == "3-bets" ||actionType == "4-bets") {
                    raiseCount++
                }

                if (raiseCount > 2) {
                    throw IllegalStateException("PreFlopPal currently doesn't handle actions beyond 3-bets.")
                }

                parsedActions.add(Pair(position, actionType))
            }
        }

        return parsedActions
    }

    fun getAdvice(hand: Hand, position: String, previousAction: String?, tableSize: TableSize): Triple<String, String, Double> {
        val handKey = hand.toKey()
        val handStrength = calculateHandStrength(hand, position)
        val actions = parsePreviousAction(previousAction ?: "",tableSize)
        val lastRaise = actions.lastOrNull { it.second == "raises" || it.second == "3-bets" }

        val advice: String
        val explanation: String

        // Get the appropriate ranges based on table size
        val rfiRanges = when(tableSize) {
            TableSize.SIX_MAX -> SIX_MAX_RFI_RANGES
            TableSize.NINE_MAX -> NINE_MAX_RFI_RANGES
        }

        val rfiCallingRanges = when(tableSize) {
            TableSize.SIX_MAX -> Six_Max_RFICallingRanges.RANGES
            TableSize.NINE_MAX -> Nine_Max_RFICallingRanges.RANGES
        }

        val rfiThreeBetRanges = when(tableSize) {
            TableSize.SIX_MAX -> Six_Max_RFIThreeBetRanges.RANGES
            TableSize.NINE_MAX -> Nine_Max_RFIThreeBetRanges.RANGES
        }

        val facingThreeBetRanges = when(tableSize) {
            TableSize.SIX_MAX -> Six_Max_FacingThreeBetRanges.RANGES
            TableSize.NINE_MAX -> Nine_Max_FacingThreeBetRanges.RANGES
        }


        when {
            actions.isEmpty() || previousAction.isNullOrBlank() || previousAction == "No action" || actions.all { it.second == "folds" } -> {
                // RFI situation
                if (position in rfiRanges && rfiRanges[position]?.contains(hand) == true) {
                    advice = "Raise"
                    explanation = "Your hand ($handKey) is strong enough to raise from $position. It's a good spot to be more aggressive."
                } else {
                    advice = "Fold"
                    explanation = "Your hand ($handKey) isn't ideal for raising from $position. It's better to fold and wait for a stronger spot."
                }
            }
            lastRaise?.second == "3-bets" -> {
                val threeBetPosition = lastRaise.first
                if (position in facingThreeBetRanges && threeBetPosition in facingThreeBetRanges[position].orEmpty()) {
                    val facingThreeBetRange = facingThreeBetRanges[position]!![threeBetPosition]!!
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
                if (position in rfiThreeBetRanges && raiserPosition in rfiThreeBetRanges[position].orEmpty()) {
                    val threeBetRange = rfiThreeBetRanges[position]!![raiserPosition]!!
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
                        rfiCallingRanges[position]?.get(raiserPosition)?.contains(hand) == true -> {
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
                gap == 1 -> 0.3  // Connector
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