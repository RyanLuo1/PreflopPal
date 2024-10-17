package com.ryanluo.prefloppal

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

    private val FACING_RFI_RANGES = Facing_RFI_Ranges.FACING_RFI_RANGES

    fun getAdvice(hand: Hand, position: String, previousAction: String?): Triple<String, String, Double> {
        val handKey = hand.toKey()
        val handStrength = calculateHandStrength(hand, position)

        val actions = parsePreviousAction(previousAction ?: "")
        val lastAction = actions.lastOrNull()

        val advice: String
        val explanation: String

        when {
            actions.isEmpty() || actions.all { it.second == "folds" } -> {
                // RFI situation
                if (position in RFI_RANGES && RFI_RANGES[position]?.contains(hand) == true) {
                    advice = "Raise"
                    explanation = "This hand ($handKey) is in the RFI range for $position. You should raise."
                } else {
                    advice = "Fold"
                    explanation = "This hand ($handKey) is not in the RFI range for $position. You should fold."
                }
            }
            lastAction?.second == "raises" -> {
                // Facing a raise
                val raiserPosition = lastAction.first
                if (position in FACING_RFI_RANGES &&
                    raiserPosition in FACING_RFI_RANGES[position]!! &&
                    FACING_RFI_RANGES[position]!![raiserPosition]?.contains(hand) == true) {
                    advice = "Call/3-Bet"
                    explanation = "This hand ($handKey) is in the calling/3-betting range against a raise from $raiserPosition when you're in $position."
                } else {
                    advice = "Fold"
                    explanation = "This hand ($handKey) is not in the calling/3-betting range against a raise from $raiserPosition when you're in $position."
                }
            }
            else -> {
                // For any other action (like calls, checks), we'll use a conservative approach
                advice = "Fold"
                explanation = "For complex scenarios, it's often safest to fold unless you're very confident. Consider the actions: ${actions.joinToString(", ") { "${it.first} ${it.second}" }}."
            }
        }

        return Triple(advice, explanation, handStrength)
    }

    private fun parsePreviousAction(previousAction: String): List<Pair<String, String>> {
        if (previousAction.isBlank() || previousAction.lowercase() == "no action") {
            return emptyList()
        }

        val actions = previousAction.split(Regex("[,.]")).map { it.trim().lowercase() }
        val positionMap = mapOf(
            "utg" to "UTG", "utg+1" to "UTG", "utg+2" to "UTG",
            "mp" to "MP", "mp+1" to "MP", "hj" to "MP", "lj" to "MP",
            "co" to "CO", "btn" to "BTN", "sb" to "SB", "bb" to "BB"
        )
        val actionKeywords = mapOf(
            "folds" to "folds",
            "fold" to "folds",
            "raises" to "raises",
            "raise" to "raises",
            "calls" to "calls",
            "call" to "calls",
            "checks" to "checks",
            "check" to "checks"
        )

        return actions.mapNotNull { action ->
            val words = action.split("\\s+".toRegex())
            var position: String? = null
            var actionType: String? = null

            for (word in words) {
                if (position == null) {
                    position = positionMap[word]
                }
                if (actionType == null) {
                    actionType = actionKeywords[word]
                }
                if (position != null && actionType != null) {
                    return@mapNotNull Pair(position, actionType)
                }
            }
            null
        }
    }

    private fun calculateHandStrength(hand: Hand, position: String): Double {
        val baseStrength = when {
            hand.isPair -> when (hand.card1.rank) {
                'A' -> 10.0
                'K' -> 9.0
                'Q' -> 8.0
                'J' -> 7.0
                'T' -> 6.0
                in '9'..'2' -> 5.0
                else -> 0.0
            }
            hand.isSuited && hand.card1.rank in "AK" && hand.card2.rank in "AK" -> 9.0
            hand.card1.rank in "AK" && hand.card2.rank in "AK" -> 8.0
            hand.isSuited && hand.card1.rank in "AK" -> 7.0
            hand.card1.rank in "AK" -> 6.0
            hand.isSuited -> 5.0
            else -> 4.0
        }

        val positionBonus = when (position) {
            "BTN", "CO" -> 1.0
            "MP" -> 0.5
            "UTG" -> 0.0
            "SB" -> -0.5
            "BB" -> -1.0
            else -> 0.0
        }

        return (baseStrength + positionBonus).coerceIn(0.0, 10.0)
    }
}