package com.ryanluo.prefloppal.models

import com.ryanluo.prefloppal.utils.Range

object Nine_Max_FacingFourBetRanges {

    val RANGES = mapOf(
        "UTG" to mapOf(
            "UTG+1" to FacingFourBetRange(
                callRange = Range(setOf("KK", "QQ")),
                allInRange = Range(setOf("AA", "AKs")),
                bluffRange = Range(setOf("AKo"))
            ),
            "UTG+2" to FacingFourBetRange(
                callRange = Range(setOf("KK", "QQ")),
                allInRange = Range(setOf("AA", "AKs")),
                bluffRange = Range(setOf("AKo"))
            ),
            "LJ" to FacingFourBetRange(
                callRange = Range(setOf("KK", "QQ")),
                allInRange = Range(setOf("AA", "AKs")),
                bluffRange = Range(setOf("AKo", "AQs"))
            ),
            "HJ" to FacingFourBetRange(
                callRange = Range(setOf("KK", "QQ")),
                allInRange = Range(setOf("AA", "AKs")),
                bluffRange = Range(setOf("AKo", "AQs"))
            ),
            "CO" to FacingFourBetRange(
                callRange = Range(setOf("KK", "QQ")),
                allInRange = Range(setOf("AA", "AKs")),
                bluffRange = Range(setOf("AKo", "AQs", "AJs"))
            ),
            "BTN" to FacingFourBetRange(
                callRange = Range(setOf("KK", "QQ")),
                allInRange = Range(setOf("AA", "AKs")),
                bluffRange = Range(setOf("AKo", "AQs", "AJs", "ATs"))
            ),
            "SB" to FacingFourBetRange(
                callRange = Range(setOf("KK")),
                allInRange = Range(setOf("AA", "AKs")),
                bluffRange = Range(setOf("AKo", "QQ"))
            ),
            "BB" to FacingFourBetRange(
                callRange = Range(setOf("KK")),
                allInRange = Range(setOf("AA", "AKs")),
                bluffRange = Range(setOf("AKo", "QQ"))
            )
        ),
        "UTG+1" to mapOf(
            "UTG+2" to FacingFourBetRange(
                callRange = Range(setOf("KK", "QQ")),
                allInRange = Range(setOf("AA", "AKs")),
                bluffRange = Range(setOf("AKo"))
            ),
            "LJ" to FacingFourBetRange(
                callRange = Range(setOf("KK", "QQ")),
                allInRange = Range(setOf("AA", "AKs")),
                bluffRange = Range(setOf("AKo", "AQs"))
            ),
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
        // Adding more advanced ranges for later positions
        "BTN" to mapOf(
            "CO" to FacingFourBetRange(
                callRange = Range(setOf("KK", "QQ", "JJ")),
                allInRange = Range(setOf("AA", "AKs", "AKo")),
                bluffRange = Range(setOf("AQs", "AJs", "KQs"))
            ),
            "SB" to FacingFourBetRange(
                callRange = Range(setOf("KK", "QQ", "JJ", "TT")),
                allInRange = Range(setOf("AA", "AKs", "AKo")),
                bluffRange = Range(setOf("AQs", "AJs", "KQs", "ATs"))
            ),
            "BB" to FacingFourBetRange(
                callRange = Range(setOf("KK", "QQ", "JJ", "TT")),
                allInRange = Range(setOf("AA", "AKs", "AKo")),
                bluffRange = Range(setOf("AQs", "AJs", "KQs", "ATs", "A5s"))
            )
        ),
        "SB" to mapOf(
            "BB" to FacingFourBetRange(
                callRange = Range(setOf("KK", "QQ", "JJ")),
                allInRange = Range(setOf("AA", "AKs", "AKo")),
                bluffRange = Range(setOf("AQs", "AJs", "KQs", "A5s"))
            )
        )
        // Note: Add other positions as needed following similar pattern
    )
}