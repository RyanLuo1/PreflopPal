package com.ryanluo.prefloppal.utils

import com.ryanluo.prefloppal.models.Nine_Max_FacingFourBetRanges
import com.ryanluo.prefloppal.models.Nine_Max_FacingThreeBetRanges
import com.ryanluo.prefloppal.models.Nine_Max_RFIThreeBetRanges
import com.ryanluo.prefloppal.models.Nine_Max_RFI_Ranges
import com.ryanluo.prefloppal.models.Six_Max_FacingFourBetRanges

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
    private val NINE_MAX_THREE_BET_RANGES = Nine_Max_RFIThreeBetRanges.RANGES
    private val NINE_MAX_FACING_THREE_BET_RANGES = Nine_Max_FacingThreeBetRanges.RANGES


    private fun parseActionSummary(actionSummary: String, tableSize: TableSize): List<Pair<String, String>> {
        if (actionSummary.isBlank()) {
            return emptyList()
        }

        val parsedActions = mutableListOf<Pair<String, String>>()
        val actions = actionSummary.split(", ")

        for (action in actions) {
            val parts = action.split(" ")
            if (parts.size >= 2) {
                val position = parts[0]  // Already standardized (UTG, MP, etc.)
                val actionType = when (parts[1]) {
                    "raises" -> "raises"
                    "3-bets" -> "3-bets"
                    "4-bets" -> "4-bets"
                    "calls" -> "calls"
                    "All" -> "all in"  // Handle "All in" case
                    else -> continue
                }
                parsedActions.add(Pair(position, actionType))
            }
        }

        // Validate against table size
        val validPositions = when (tableSize) {
            TableSize.NINE_MAX -> NINE_MAX_POSITIONS
            TableSize.SIX_MAX -> SIX_MAX_POSITIONS
        }

        return parsedActions.filter { it.first in validPositions }
    }

    fun getAdvice(hand: Hand, position: String, actionSummary: String?, tableSize: TableSize): Triple<String, String, Double> {
        val handKey = hand.toKey()
        val handStrength = calculateHandStrength(hand, position)
        val actions = parseActionSummary(actionSummary ?: "", tableSize)
        val lastRaise = actions.lastOrNull { it.second in listOf("raises", "3-bets", "4-bets") }

        val advice: String
        val explanation: String

        // Get the appropriate ranges based on table size
        val rfiRanges = when(tableSize) {
            TableSize.SIX_MAX -> SIX_MAX_RFI_RANGES
            TableSize.NINE_MAX -> NINE_MAX_RFI_RANGES
        }

        val facingRaisingRanges = when(tableSize) {
            TableSize.SIX_MAX -> Six_Max_RFIThreeBetRanges.RANGES
            TableSize.NINE_MAX -> Nine_Max_RFIThreeBetRanges.RANGES
        }

        val facingThreeBetRanges = when(tableSize) {
            TableSize.SIX_MAX -> Six_Max_FacingThreeBetRanges.RANGES
            TableSize.NINE_MAX -> Nine_Max_FacingThreeBetRanges.RANGES
        }

        val facingFourBetRanges = when(tableSize) {
            TableSize.SIX_MAX -> Six_Max_FacingFourBetRanges.RANGES
            TableSize.NINE_MAX -> Nine_Max_FacingFourBetRanges.RANGES
        }

        when {
            // RFI situation
            actions.isEmpty() || actionSummary.isNullOrBlank() || actionSummary == "No action" || actions.all { it.second == "folds" } -> {
                if (position in rfiRanges && rfiRanges[position]?.contains(hand) == true) {
                    advice = "Raise"
                    explanation = "Your hand ($handKey) is strong enough to raise from $position. It's a good spot to be more aggressive."
                } else {
                    advice = "Fold"
                    explanation = "Your hand ($handKey) isn't ideal for raising from $position. It's better to fold and wait for a stronger spot."
                }
            }

            // Facing a raise
            lastRaise?.second == "raises" -> {
                val raiserPosition = lastRaise.first
                if (position in facingRaisingRanges && raiserPosition in facingRaisingRanges[position].orEmpty()) {
                    val threeBetRange = facingRaisingRanges[position]!![raiserPosition]!!
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
                        threeBetRange.callRange.contains(hand) -> {
                            advice = "Call"
                            explanation = "This hand ($handKey) is in the calling range against a raise from $raiserPosition when you're in $position."
                        }
                        threeBetRange.rcRange.contains(hand) -> {
                            advice = "Mix of Raise/Call"
                            explanation = "This hand ($handKey) is in the Raise/Call mix range against a raise from $raiserPosition when you're in $position."
                        }
                        threeBetRange.fcRange.contains(hand) -> {
                            advice = "Mix of Fold/Call"
                            explanation = "This hand ($handKey) is in the Fold/Call mix range against a raise from $raiserPosition when you're in $position."
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

            // Facing a 3bet
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
                        facingThreeBetRange.rcRange.contains(hand) -> {
                            advice = "Raise or Call"
                            explanation = "This hand ($handKey) is solid enough to Raise/call mix against a 3-bet from $threeBetPosition when you're in $position."
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

            // Facing a 4bet
            lastRaise?.second == "4-bets" -> {
                val fourBetPosition = lastRaise.first

                // Find who made the 3bet
                val threeBetAction = actions.find { it.second == "3-bets" }
                val is3Bettor = threeBetAction?.first == position

                if (is3Bettor && position in facingFourBetRanges && fourBetPosition in facingFourBetRanges[position].orEmpty()) {
                    // User was the 3bettor - use the facing 4bet file
                    val facingFourBetRange = facingFourBetRanges[position]!![fourBetPosition]!!
                    when {
                        facingFourBetRange.allInRange.contains(hand) -> {
                            advice = "All-In"
                            explanation = "As the 3-bettor, your hand ($handKey) is strong enough to move all-in against a 4-bet from $fourBetPosition. " +
                                    "Since you 3-bet this hand for value, you can continue with a 5-bet all-in."
                        }
                        facingFourBetRange.callRange.contains(hand) -> {
                            advice = "Call"
                            explanation = "As the 3-bettor, this hand ($handKey) is strong enough to call a 4-bet from $fourBetPosition. " +
                                    "While not strong enough to move all-in, it has good playability and equity to continue."
                        }
                        facingFourBetRange.bluffRange.contains(hand) -> {
                            advice = "All-In as a Bluff or Fold"
                            explanation = "As the 3-bettor, this hand ($handKey) can be used as an all-in bluff against a 4-bet from $fourBetPosition. " +
                                    "While risky, occasionally moving all-in with these hands helps balance your range and prevents opponents from exploiting you."
                        }
                        else -> {
                            advice = "Fold"
                            explanation = "Even though you were the 3-bettor, this hand ($handKey) isn't strong enough to continue against a 4-bet from $fourBetPosition. " +
                                    "The pot odds and implied odds don't justify continuing with this hand."
                        }
                    }
                } else {
                    // User facing a cold 4bet - use simplified logic
                    when {
                        setOf("AA", "KK", "AKs").contains(handKey) -> {
                            advice = "All-In"
                            explanation = "Facing a cold 4-bet from $fourBetPosition, only the absolute strongest hands ($handKey) can move all-in. " +
                                    "Your hand is at the top of your range and performs well enough against both the original 3-bettor and the 4-bettor."
                        }
                        handKey == "QQ" -> {
                            advice = "Call"
                            explanation = "Facing a cold 4-bet from $fourBetPosition, this hand ($handKey) is strong enough to call. " +
                                    "While not strong enough to move all-in in a multi-way pot, it has enough equity to continue."
                        }
                        else -> {
                            advice = "Fold"
                            explanation = "Facing a cold 4-bet from $fourBetPosition, this hand ($handKey) isn't strong enough to continue. " +
                                    "With multiple players showing strength, you should fold all but your strongest hands."
                        }
                    }
                }
            }

            // Facing an all-in
            lastRaise?.second == "all-in" -> {
                val allInPosition = lastRaise.first
                when {
                    setOf("AA", "KK", "AKs").contains(handKey) -> {
                        advice = "Call"
                        explanation = "With ${handKey}, you can call the all-in from $allInPosition. " +
                                "These premium hands have enough equity to profitably call an all-in."
                    }
                    else -> {
                        advice = "Fold"
                        explanation = "Even though ${handKey} might be a strong hand, it's not strong enough to call an all-in from $allInPosition. " +
                                "Only the absolute premium hands (AA, KK, AKs) should continue here."
                    }
                }
            }

            // Unexpected scenario
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