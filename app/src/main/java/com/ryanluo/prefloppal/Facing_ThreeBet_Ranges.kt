package com.ryanluo.prefloppal

object FacingThreeBetRanges {
    data class FacingThreeBetRange(
        val coldCallRange: Range,
        val fourBetValueRange: Range,
        val fourBetBluffRange: Range
    )

    val RANGES = mapOf(
        "UTG" to mapOf(
            "MP" to FacingThreeBetRange(
                coldCallRange = Range(setOf("QQ", "JJ", "TT", "AKs", "AQs", "AKo")),
                fourBetValueRange = Range(setOf("AA", "KK")),
                fourBetBluffRange = Range(setOf("AKs"))
            ),
            "CO" to FacingThreeBetRange(
                coldCallRange = Range(setOf("QQ", "JJ", "TT", "AKs", "AQs", "AJs", "KQs", "AKo")),
                fourBetValueRange = Range(setOf("AA", "KK")),
                fourBetBluffRange = Range(setOf("AKs", "AQs"))
            ),
            "BTN" to FacingThreeBetRange(
                coldCallRange = Range(setOf("QQ", "JJ", "TT", "99", "AKs", "AQs", "AJs", "KQs", "AKo", "AQo")),
                fourBetValueRange = Range(setOf("AA", "KK")),
                fourBetBluffRange = Range(setOf("AKs", "AQs", "AJs"))
            ),
            "SB" to FacingThreeBetRange(
                coldCallRange = Range(setOf("QQ", "JJ", "TT", "99", "AKs", "AQs", "AJs", "KQs", "AKo", "AQo")),
                fourBetValueRange = Range(setOf("AA", "KK")),
                fourBetBluffRange = Range(setOf("AKs", "AQs", "AJs", "KQs"))
            ),
            "BB" to FacingThreeBetRange(
                coldCallRange = Range(setOf("QQ", "JJ", "TT", "99", "AKs", "AQs", "AJs", "KQs", "AKo", "AQo")),
                fourBetValueRange = Range(setOf("AA", "KK")),
                fourBetBluffRange = Range(setOf("AKs", "AQs", "AJs", "KQs"))
            )
        ),
        "MP" to mapOf(
            "CO" to FacingThreeBetRange(
                coldCallRange = Range(setOf("QQ", "JJ", "TT", "AKs", "AQs", "AJs", "KQs", "AKo")),
                fourBetValueRange = Range(setOf("AA", "KK")),
                fourBetBluffRange = Range(setOf("AKs", "AQs"))
            ),
            "BTN" to FacingThreeBetRange(
                coldCallRange = Range(setOf("QQ", "JJ", "TT", "99", "AKs", "AQs", "AJs", "KQs", "AKo", "AQo")),
                fourBetValueRange = Range(setOf("AA", "KK")),
                fourBetBluffRange = Range(setOf("AKs", "AQs", "AJs"))
            ),
            "SB" to FacingThreeBetRange(
                coldCallRange = Range(setOf("QQ", "JJ", "TT", "99", "AKs", "AQs", "AJs", "KQs", "AKo", "AQo")),
                fourBetValueRange = Range(setOf("AA", "KK")),
                fourBetBluffRange = Range(setOf("AKs", "AQs", "AJs", "KQs"))
            )
        ),
        "CO" to mapOf(
            "MP" to FacingThreeBetRange(
                coldCallRange = Range(setOf("QQ", "JJ", "TT", "99", "AKs", "AQs", "AJs", "KQs", "AKo", "AQo")),
                fourBetValueRange = Range(setOf("AA", "KK")),
                fourBetBluffRange = Range(setOf("AKs", "AQs", "AJs"))
            ),
            "BTN" to FacingThreeBetRange(
                coldCallRange = Range(setOf("QQ", "JJ", "TT", "99", "AKs", "AQs", "AJs", "KQs", "AKo", "AQo")),
                fourBetValueRange = Range(setOf("AA", "KK")),
                fourBetBluffRange = Range(setOf("AKs", "AQs", "AJs"))
            ),
            "SB" to FacingThreeBetRange(
                coldCallRange = Range(setOf("QQ", "JJ", "TT", "99", "88", "AKs", "AQs", "AJs", "ATs", "KQs", "KJs", "AKo", "AQo")),
                fourBetValueRange = Range(setOf("AA", "KK")),
                fourBetBluffRange = Range(setOf("AKs", "AQs", "AJs", "KQs"))
            )
        ),
        "BTN" to mapOf(
            "SB" to FacingThreeBetRange(
                coldCallRange = Range(setOf("QQ", "JJ", "TT", "99", "88", "AKs", "AQs", "AJs", "ATs", "KQs", "KJs", "AKo", "AQo", "KQo")),
                fourBetValueRange = Range(setOf("AA", "KK")),
                fourBetBluffRange = Range(setOf("AKs", "AQs", "AJs", "KQs", "ATs"))
            )
        )
    )
}