package com.ryanluo.prefloppal.models

import com.ryanluo.prefloppal.utils.Range

// First create Six Max ranges
object Six_Max_FacingFourBetRanges {
    val RANGES = mapOf(
        "LJ" to mapOf(
            "HJ" to FacingFourBetRange(
                callRange = Range(setOf("KK", "QQ")),
                allInRange = Range(setOf("AA", "AKs")),
                bluffRange = Range(setOf("AKo", "AQs"))
            ),
            "CO" to FacingFourBetRange(
                callRange = Range(setOf("KK", "QQ", "JJ")),
                allInRange = Range(setOf("AA", "AKs")),
                bluffRange = Range(setOf("AKo", "AQs", "AJs"))
            ),
            "BTN" to FacingFourBetRange(
                callRange = Range(setOf("KK", "QQ", "JJ")),
                allInRange = Range(setOf("AA", "AKs")),
                bluffRange = Range(setOf("AKo", "AQs", "AJs", "ATs"))
            ),
            "SB" to FacingFourBetRange(
                callRange = Range(setOf("KK", "QQ")),
                allInRange = Range(setOf("AA", "AKs")),
                bluffRange = Range(setOf("AKo", "AQs"))
            ),
            "BB" to FacingFourBetRange(
                callRange = Range(setOf("KK", "QQ")),
                allInRange = Range(setOf("AA", "AKs")),
                bluffRange = Range(setOf("AKo", "AQs"))
            )
        ),
        "HJ" to mapOf(
            "CO" to FacingFourBetRange(
                callRange = Range(setOf("KK", "QQ", "JJ")),
                allInRange = Range(setOf("AA", "AKs")),
                bluffRange = Range(setOf("AKo", "AQs", "AJs"))
            ),
            "BTN" to FacingFourBetRange(
                callRange = Range(setOf("KK", "QQ", "JJ")),
                allInRange = Range(setOf("AA", "AKs")),
                bluffRange = Range(setOf("AKo", "AQs", "AJs", "ATs"))
            ),
            "SB" to FacingFourBetRange(
                callRange = Range(setOf("KK", "QQ")),
                allInRange = Range(setOf("AA", "AKs")),
                bluffRange = Range(setOf("AKo", "AQs"))
            ),
            "BB" to FacingFourBetRange(
                callRange = Range(setOf("KK", "QQ")),
                allInRange = Range(setOf("AA", "AKs")),
                bluffRange = Range(setOf("AKo", "AQs"))
            )
        ),
        // Add other positions following similar pattern
        "CO" to mapOf(
            "BTN" to FacingFourBetRange(
                callRange = Range(setOf("KK", "QQ", "JJ")),
                allInRange = Range(setOf("AA", "AKs", "AKo")),
                bluffRange = Range(setOf("AQs", "AJs", "KQs"))
            ),
            "SB" to FacingFourBetRange(
                callRange = Range(setOf("KK", "QQ")),
                allInRange = Range(setOf("AA", "AKs")),
                bluffRange = Range(setOf("AKo", "AQs"))
            ),
            "BB" to FacingFourBetRange(
                callRange = Range(setOf("KK", "QQ")),
                allInRange = Range(setOf("AA", "AKs")),
                bluffRange = Range(setOf("AKo", "AQs"))
            )
        ),
        "BTN" to mapOf(
            "SB" to FacingFourBetRange(
                callRange = Range(setOf("KK", "QQ", "JJ")),
                allInRange = Range(setOf("AA", "AKs", "AKo")),
                bluffRange = Range(setOf("AQs", "AJs", "KQs"))
            ),
            "BB" to FacingFourBetRange(
                callRange = Range(setOf("KK", "QQ", "JJ", "TT")),
                allInRange = Range(setOf("AA", "AKs", "AKo")),
                bluffRange = Range(setOf("AQs", "AJs", "KQs", "A5s"))
            )
        ),
        "SB" to mapOf(
            "BB" to FacingFourBetRange(
                callRange = Range(setOf("KK", "QQ", "JJ")),
                allInRange = Range(setOf("AA", "AKs", "AKo")),
                bluffRange = Range(setOf("AQs", "AJs", "KQs", "A5s"))
            )
        )
    )
}