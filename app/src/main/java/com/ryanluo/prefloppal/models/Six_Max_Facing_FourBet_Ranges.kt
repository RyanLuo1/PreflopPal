package com.ryanluo.prefloppal.models

import com.ryanluo.prefloppal.utils.Range

object Six_Max_FacingFourBetRanges {
    val RANGES = mapOf(

        "UTG" to mapOf(

            // Can't face MP 4-bet

            "CO" to FacingFourBetRange(
                callRange = Range(setOf()),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK")),
                bluffRange = Range(setOf())
            ),
            "BTN" to FacingFourBetRange(
                callRange = Range(setOf()),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK")),
                bluffRange = Range(setOf())
            ),
            "SB" to FacingFourBetRange(
                callRange = Range(setOf()),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf("AKo"))
            ),
            "BB" to FacingFourBetRange(
                callRange = Range(setOf()),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK")),
                bluffRange = Range(setOf())
            )
        ),
        "MP" to mapOf(
            "UTG" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "AJs", "KQs", "QQ", "JJ")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK")),
                bluffRange = Range(setOf())
            ),
            "CO" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "A5s", "QQ", "JJ", "TT", "99")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK")),
                bluffRange = Range(setOf())
            ),
            "BTN" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "A5s", "QQ", "JJ", "TT")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK")),
                bluffRange = Range(setOf())
            ),
            "SB" to FacingFourBetRange(
                callRange = Range(setOf("QQ")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK")),
                bluffRange = Range(setOf())
            ),
            "BB" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "QQ")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK")),
                bluffRange = Range(setOf())
            )
        ),
        "CO" to mapOf(
            "UTG" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "AJs", "KQs", "QQ", "JJ")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK")),
                bluffRange = Range(setOf())
            ),
            "MP" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "AJs", "A5s", "KQs", "KJs", "QQ", "JJ", "TT")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK")),
                bluffRange = Range(setOf())
            ),
            "BTN" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "A5s", "KQs", "QQ", "JJ", "TT")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK")),
                bluffRange = Range(setOf())
            ),
            "SB" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "QQ")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK")),
                bluffRange = Range(setOf())
            ),
            "BB" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "QQ", "JJ")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK")),
                bluffRange = Range(setOf())
            )
        ),
        "BTN" to mapOf(
            "UTG" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "AJs", "ATs", "A5s", "KQs", "KJs", "QQ", "JJ", "TT")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK")),
                bluffRange = Range(setOf())
            ),
            "MP" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "AJs", "ATs", "A5s", "A4s", "KQs", "KJs", "KTs", "QQ", "JJ", "JTs", "TT", "99")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK")),
                bluffRange = Range(setOf())
            ),
            "CO" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "AQo", "AJs", "ATs", "A5s", "A4s", "KQs", "KJs", "KTs", "QJs", "JJ", "JTs", "TT", "99", "88")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ")),
                bluffRange = Range(setOf())
            ),
            "SB" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "QQ", "JJ")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK")),
                bluffRange = Range(setOf())
            ),
            "BB" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "QQ", "JJ")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK")),
                bluffRange = Range(setOf())
            )
        ),
        "SB" to mapOf(
            "UTG" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "AJs", "ATs", "A5s", "QQ", "JJ", "TT")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK")),
                bluffRange = Range(setOf())
            ),
            "MP" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "AJs", "ATs", "A5s", "KQs", "KJs", "JJ", "TT")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ")),
                bluffRange = Range(setOf())
            ),
            "CO" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "AJs", "A4s", "KQs", "TT", "99")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ", "JJ")),
                bluffRange = Range(setOf("ATs", "A5s"))
            ),
            "BTN" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "AQo", "AJs", "ATs", "KQs", "KJs", "KTs", "99", "88", "77")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ", "JJ", "TT")),
                bluffRange = Range(setOf("A5s", "A4s",))
            ),
            "BB" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "QQ")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK")),
                bluffRange = Range(setOf())
            )
        ),
        "BB" to mapOf(
            "UTG" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "QQ", "JJ")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK")),
                bluffRange = Range(setOf())
            ),
            "MP" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "KJs", "JJ")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ")),
                bluffRange = Range(setOf("A5s"))
            ),
            "CO" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "AJs", "TT")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ", "JJ")),
                bluffRange = Range(setOf("A5s", "A4s",))
            ),
            "BTN" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "AQo", "AJs", "ATs", "KQs", "KJs", "KTs", "JTs", "T9s", "99", "88")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ", "JJ", "TT")),
                bluffRange = Range(setOf("A5s", "A4s"))
            ),
            "SB" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "AQo", "AJs", "ATs", "A5s", "KQs", "KJs", "KTs", "QJs", "QTs", "JTs", "TT", "T9s", "99", "98s", "88", "87s", "76s", "65s", "54s")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ", "JJ")),
                bluffRange = Range(setOf("A4s"))
            ),
        ),
    )
}