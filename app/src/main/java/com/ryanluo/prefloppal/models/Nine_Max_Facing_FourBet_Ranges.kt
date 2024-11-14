package com.ryanluo.prefloppal.models

import com.ryanluo.prefloppal.utils.Range

object Nine_Max_FacingFourBetRanges {

    val RANGES = mapOf(
        "UTG" to mapOf(

            //Can't face UTG+1 4bet

            "UTG+2" to FacingFourBetRange(
                callRange = Range(setOf()),
                allInRange = Range(setOf("AA", "KK")),
                bluffRange = Range(setOf("AKs"))
            ),
            "LJ" to FacingFourBetRange(
                callRange = Range(setOf()),
                allInRange = Range(setOf("AA", "KK")),
                bluffRange = Range(setOf("AKs"))
            ),
            "HJ" to FacingFourBetRange(
                callRange = Range(setOf()),
                allInRange = Range(setOf("AA", "KK")),
                bluffRange = Range(setOf("AKs"))
            ),
            "CO" to FacingFourBetRange(
                callRange = Range(setOf()),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf("A5s"))
            ),
            "BTN" to FacingFourBetRange(
                callRange = Range(setOf()),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf())
            ),
            "SB" to FacingFourBetRange(
                callRange = Range(setOf()),
                allInRange = Range(setOf("AA", "KK")),
                bluffRange = Range(setOf())
            ),
            "BB" to FacingFourBetRange(
                callRange = Range(setOf()),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf())
            )
        ),
        "UTG+1" to mapOf(
            "UTG" to FacingFourBetRange(
                callRange = Range(setOf("QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf("AKo"))
            ),
            "UTG+2" to FacingFourBetRange(
                callRange = Range(setOf("AKs", "QQ")),
                allInRange = Range(setOf("AA", "KK")),
                bluffRange = Range(setOf("AKo"))
            ),
            "LJ" to FacingFourBetRange(
                callRange = Range(setOf("QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf("AKo"))
            ),
            "HJ" to FacingFourBetRange(
                callRange = Range(setOf("QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf("AKo"))
            ),
            "CO" to FacingFourBetRange(
                callRange = Range(setOf("QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf("AKo"))
            ),
            "BTN" to FacingFourBetRange(
                callRange = Range(setOf("QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf())
            ),
            "SB" to FacingFourBetRange(
                callRange = Range(setOf("QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf())
            ),
            "BB" to FacingFourBetRange(
                callRange = Range(setOf("QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf())
            )
        ),
        "UTG+2" to mapOf(
            "UTG" to FacingFourBetRange(
                callRange = Range(setOf("QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf())
            ),
            "UTG+1" to FacingFourBetRange(
                callRange = Range(setOf("QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf())
            ),
            "LJ" to FacingFourBetRange(
                callRange = Range(setOf("QQ", "AKs")),
                allInRange = Range(setOf("AA", "KK")),
                bluffRange = Range(setOf("AKo"))
            ),
            "HJ" to FacingFourBetRange(
                callRange = Range(setOf("QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf("AKo"))
            ),
            "CO" to FacingFourBetRange(
                callRange = Range(setOf("QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf("AKo"))
            ),
            "BTN" to FacingFourBetRange(
                callRange = Range(setOf("QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf("AKo"))
            ),
            "SB" to FacingFourBetRange(
                callRange = Range(setOf()),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf())
            ),
            "BB" to FacingFourBetRange(
                callRange = Range(setOf("QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf())
            )
        ),
        "LJ" to mapOf(
            "UTG" to FacingFourBetRange(
                callRange = Range(setOf("QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf())
            ),
            "UTG+1" to FacingFourBetRange(
                callRange = Range(setOf("QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf())
            ),
            "UTG+2" to FacingFourBetRange(
                callRange = Range(setOf("AQs","QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf())
            ),
            "HJ" to FacingFourBetRange(
                callRange = Range(setOf("QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf("AKo"))
            ),
            "CO" to FacingFourBetRange(
                callRange = Range(setOf("QQ", "JJ")),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf("AKo"))
            ),
            "BTN" to FacingFourBetRange(
                callRange = Range(setOf("QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf("ATs"))
            ),
            "SB" to FacingFourBetRange(
                callRange = Range(setOf()),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf())
            ),
            "BB" to FacingFourBetRange(
                callRange = Range(setOf("QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf())
            )
        ),
        "HJ" to mapOf(
            "UTG" to FacingFourBetRange(
                callRange = Range(setOf("QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf())
            ),
            "UTG+1" to FacingFourBetRange(
                callRange = Range(setOf("QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf())
            ),
            "UTG+2" to FacingFourBetRange(
                callRange = Range(setOf("AQs","QQ", "65s")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf())
            ),
            "LJ" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "AJs", "KQs", "QQ", "JJ", "99")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf())
            ),
            "CO" to FacingFourBetRange(
                callRange = Range(setOf("QQ", "JJ")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf())
            ),
            "BTN" to FacingFourBetRange(
                callRange = Range(setOf("QQ", "JJ")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf())
            ),
            "SB" to FacingFourBetRange(
                callRange = Range(setOf("QQ", "JJ")),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf())
            ),
            "BB" to FacingFourBetRange(
                callRange = Range(setOf("QQ", "JJ")),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf())
            )
        ),
        "CO" to mapOf(
            "UTG" to FacingFourBetRange(
                callRange = Range(setOf("QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf())
            ),
            "UTG+1" to FacingFourBetRange(
                callRange = Range(setOf("AQs","QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf())
            ),
            "UTG+2" to FacingFourBetRange(
                callRange = Range(setOf("AQs","AJs","QQ", "65s")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf())
            ),
            "LJ" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "AJs", "ATs", "A5s", "KQs", "KJs","QQ", "JJ", "99")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf())
            ),
            "HJ" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "AJs", "A5s", "KQs", "KJs", "QQ", "JJ", "TT", "99")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf())
            ),
            "BTN" to FacingFourBetRange(
                callRange = Range(setOf("QQ", "JJ", "TT")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf())
            ),
            "SB" to FacingFourBetRange(
                callRange = Range(setOf("QQ", "JJ", "TT")),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf())
            ),
            "BB" to FacingFourBetRange(
                callRange = Range(setOf("QQ", "JJ", "TT")),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf())
            )
        ),
        "BTN" to mapOf(
            "UTG" to FacingFourBetRange(
                callRange = Range(setOf("QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf())
            ),
            "UTG+1" to FacingFourBetRange(
                callRange = Range(setOf("AQs","QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf())
            ),
            "UTG+2" to FacingFourBetRange(
                callRange = Range(setOf("AQs","AJs","QQ", "JJ", "65s")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf())
            ),
            "LJ" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "AJs", "ATs", "A5s", "KQs", "KJs","QQ", "JJ", "99")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf("A5s"))
            ),
            "HJ" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "AJs", "A5s", "KQs", "KJs", "JJ", "TT", "99")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo", "QQ")),
                bluffRange = Range(setOf("A5s"))
            ),
            "CO" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "AJs", "KQs", "KJs", "KTs", "TT", "99")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ", "JJ")),
                bluffRange = Range(setOf("A5s", "A4s",))
            ),
            "SB" to FacingFourBetRange(
                callRange = Range(setOf("QQ", "JJ", "TT")),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf())
            ),
            "BB" to FacingFourBetRange(
                callRange = Range(setOf("QQ", "JJ", "TT")),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf())
            )
        ),
        "SB" to mapOf(
            "UTG" to FacingFourBetRange(
                callRange = Range(setOf("QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf())
            ),
            "UTG+1" to FacingFourBetRange(
                callRange = Range(setOf("AQs","QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf())
            ),
            "UTG+2" to FacingFourBetRange(
                callRange = Range(setOf("AQs","AJs","QQ", "JJ", "65s")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf("A5s"))
            ),
            "LJ" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "AJs", "ATs", "A5s", "KQs","QQ", "JJ", "99")),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf("AKo","A4s"))
            ),
            "HJ" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "AJs", "KQs", "KJs", "JJ", "TT", "99")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo", "QQ")),
                bluffRange = Range(setOf("A5s", "A4s"))
            ),
            "CO" to FacingFourBetRange(
                callRange = Range(setOf("AQs")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ", "JJ")),
                bluffRange = Range(setOf("A5s", "A4s",))
            ),
            "BTN" to FacingFourBetRange(
                callRange = Range(setOf("AJs","KQs")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK","QQ", "JJ", "TT")),
                bluffRange = Range(setOf("AQs", "AQo", "A5s","KJs", ))
            ),
            "BB" to FacingFourBetRange(
                callRange = Range(setOf("QQ", "JJ", "TT")),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf())
            )
        ),
        "BB" to mapOf(
            "UTG" to FacingFourBetRange(
                callRange = Range(setOf()),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf("A4s", "A2s"))
            ),
            "UTG+1" to FacingFourBetRange(
                callRange = Range(setOf()),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf("A3s"))
            ),
            "UTG+2" to FacingFourBetRange(
                callRange = Range(setOf("QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf("A3s"))
            ),
            "LJ" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "AJs", "ATs", "A5s", "KQs","QQ", "JJ", "99")),
                allInRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf("AKo","A4s"))
            ),
            "HJ" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "QQ")),
                allInRange = Range(setOf("AA", "AKs", "KK", "AKo")),
                bluffRange = Range(setOf("A5s", "A4s"))
            ),
            "CO" to FacingFourBetRange(
                callRange = Range(setOf("AQs")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ", "JJ")),
                bluffRange = Range(setOf("A5s", "A4s",))
            ),
            "BTN" to FacingFourBetRange(
                callRange = Range(setOf("AJs","KQs")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK","QQ", "JJ", "TT")),
                bluffRange = Range(setOf("AQs", "A5s"))
            ),
            "SB" to FacingFourBetRange(
                callRange = Range(setOf("AQs", "AJs", "KQs")),
                allInRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ", "JJ", "TT")),
                bluffRange = Range(setOf("AQo", "A5s", "A4s","QJs"))
            )
        )
    )
}