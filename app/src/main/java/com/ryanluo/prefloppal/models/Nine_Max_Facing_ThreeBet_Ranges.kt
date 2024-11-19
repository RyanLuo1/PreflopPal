package com.ryanluo.prefloppal.models

import com.ryanluo.prefloppal.utils.Range

object Nine_Max_FacingThreeBetRanges {

    val RANGES = mapOf(

        "UTG" to mapOf(
            "UTG+1" to FacingThreeBetRange(
                callRange = Range(setOf("QQ")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("A5s"))

            ),
            "UTG+2" to FacingThreeBetRange(
                callRange = Range(setOf("QQ")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("A4s"))
            ),
            "LJ" to FacingThreeBetRange(
                callRange = Range(setOf("QQ", "AQs")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("A4s"))
            ),
            "HJ" to FacingThreeBetRange(
                callRange = Range(setOf("AQs")),
                rcRange = Range(setOf("QQ")),
                fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("A5s","A4s"))
            ),
            "CO" to FacingThreeBetRange(
                callRange = Range(setOf()),
                rcRange = Range(setOf("AQs", "QQ")),
                fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("A5s", "A4s",))
            ),
            "BTN" to FacingThreeBetRange(
                callRange = Range(setOf("QQ")),
                rcRange = Range(setOf("AQs" )),
                fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("AJs", "ATs", "KJs"))
            ),
            "SB" to FacingThreeBetRange(
                callRange = Range(setOf("QQ")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf())
            ),
            "BB" to FacingThreeBetRange(
                callRange = Range(setOf("QQ")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf())
            )
        ),
        "UTG+1" to mapOf(
            "UTG+2" to FacingThreeBetRange(
                callRange = Range(setOf("AQs")),
                rcRange = Range(setOf("QQ")),
                fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("A4s"))
            ),
            "LJ" to FacingThreeBetRange(
                callRange = Range(setOf("AQs")),
                rcRange = Range(setOf("QQ")),
                fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("A4s"))
            ),
            "HJ" to FacingThreeBetRange(
                callRange = Range(setOf("AQs")),
                rcRange = Range(setOf("QQ")),
                fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("A4s", "KQs"))
            ),
            "CO" to FacingThreeBetRange(
                callRange = Range(setOf()),
                rcRange = Range(setOf("AQs","QQ")),
                fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("A4s", "KQs"))
            ),
            "BTN" to FacingThreeBetRange(
                callRange = Range(setOf("AQs","QQ", "JJ")),
                rcRange = Range(setOf("AJs")),
                fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("ATs","KJs", "KTs"))
            ),
            "SB" to FacingThreeBetRange(
                callRange = Range(setOf("AQs","QQ")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf())
            ),
            "BB" to FacingThreeBetRange(
                callRange = Range(setOf("QQ")),
                rcRange = Range(setOf( "AQs")),
                fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf())
            )
        ),
        "UTG+2" to mapOf(
            "UTG+1" to FacingThreeBetRange(
                callRange = Range(setOf()),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo", "QQ")),
                fourBetBluffRange = Range(setOf())

            ),
            "LJ" to FacingThreeBetRange(
                callRange = Range(setOf("AQs" , "QQ")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("A4s", "KQs"))
            ),
            "HJ" to FacingThreeBetRange(
                callRange = Range(setOf()),
                rcRange = Range(setOf("AQs", "QQ")),
                fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("A4s", "KQs"))
            ),
            "CO" to FacingThreeBetRange(
                callRange = Range(setOf()),
                rcRange = Range(setOf("AQs", "QQ")),
                fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("AJs", "A5s", "A4s", "KQs", "KTs"))

            ),
            "BTN" to FacingThreeBetRange(
                callRange = Range(setOf("AQs", "QQ", "JJ")),
                rcRange = Range(setOf("AJs", "ATs")),
                fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("KQs","KJs", "KTs"))
            ),
            "SB" to FacingThreeBetRange(
                callRange = Range(setOf("AQs", "QQ")),
                rcRange = Range(setOf("AKo","AJs")),
                fourBetValueRange = Range(setOf("AA", "KK", "AKs")),
                fourBetBluffRange = Range(setOf("AJs", "KJs"))
            ),
            "BB" to FacingThreeBetRange(
                callRange = Range(setOf("AQs", "QQ")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("AJs", "ATs"))

                ),
            ),
            "LJ" to mapOf(
                "UTG+1" to FacingThreeBetRange(
                    callRange = Range(setOf()),
                    rcRange = Range(setOf( )),
                    fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo", "QQ")),
                    fourBetBluffRange = Range(setOf())

                ),
                "UTG+2" to FacingThreeBetRange(
                    callRange = Range(setOf()),
                    rcRange = Range(setOf( )),
                    fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo", "QQ")),
                    fourBetBluffRange = Range(setOf("AQs"))
                ),
                "HJ" to FacingThreeBetRange(
                    callRange = Range(setOf("AQs")),
                    rcRange = Range(setOf("JJ")),
                    fourBetValueRange = Range(setOf("AA", "KK", "QQ", "AKs", "AKo")),
                    fourBetBluffRange = Range(setOf("AQo", "AJs", "A5s", "KQs","KJs"))
                ),
                "CO" to FacingThreeBetRange(
                    callRange = Range(setOf("AQs","TT")),
                    rcRange = Range(setOf("JJ")),
                    fourBetValueRange = Range(setOf("AA", "KK", "QQ", "AKs", "AKo")),
                    fourBetBluffRange = Range(setOf("AQo", "AJs", "ATs", "A5s", "KQs", "KJs", "KTs"))
                ),
                "BTN" to FacingThreeBetRange(
                    callRange = Range(setOf("AQs")),
                    rcRange = Range(setOf("JJ", "TT")),
                    fourBetValueRange = Range(setOf("AA", "KK", "QQ", "AKs", "AKo")),
                    fourBetBluffRange = Range(setOf("AQo", "AJs", "ATs", "KQs", "KJs", "KTs"))
                ),
                "SB" to FacingThreeBetRange(
                    callRange = Range(setOf("AQs", "KQs")),
                    rcRange = Range(setOf("AJs", "ATs", "QQ", "JJ", "TT" )),
                    fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo")),
                    fourBetBluffRange = Range(setOf())
                ),
                "BB" to FacingThreeBetRange(
                    callRange = Range(setOf("AQs", "AJs", "KQs", "QQ", "JJ")),
                    rcRange = Range(setOf("AKo")),
                    fourBetValueRange = Range(setOf("AA", "KK", "AKs")),
                    fourBetBluffRange = Range(setOf())
                )
            ),
            "HJ" to mapOf(
                "UTG+1" to FacingThreeBetRange(
                    callRange = Range(setOf()),
                    rcRange = Range(setOf( )),
                    fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo")),
                    fourBetBluffRange = Range(setOf("AQs"))

                ),
                "UTG+2" to FacingThreeBetRange(
                    callRange = Range(setOf()),
                    rcRange = Range(setOf( )),
                    fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo", "QQ")),
                    fourBetBluffRange = Range(setOf("AQs"))
                ),
                "LJ" to FacingThreeBetRange(
                    callRange = Range(setOf()),
                    rcRange = Range(setOf( )),
                    fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo", "QQ")),
                    fourBetBluffRange = Range(setOf())
                ),
                "CO" to FacingThreeBetRange(
                    callRange = Range(setOf("AQs")),
                    rcRange = Range(setOf("AJs", "ATs", "KQs", "KTs" )),
                    fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ", "JJ", "TT")),
                    fourBetBluffRange = Range(setOf("AQo", "KJs"))
                ),
                "BTN" to FacingThreeBetRange(
                    callRange = Range(setOf("AQs")),
                    rcRange = Range(setOf("AJs", "ATs", "KQs")),
                    fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ", "JJ", "TT")),
                    fourBetBluffRange = Range(setOf("AQo","KJs", "KTs"))
                ),
                "SB" to FacingThreeBetRange(
                    callRange = Range(setOf("AQs", "AJs", "ATs", "KQs", "QQ", "JJ", "TT", "99")),
                    rcRange = Range(setOf("KJs")),
                    fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo")),
                    fourBetBluffRange = Range(setOf("AQo","A5s"))
                ),
                "BB" to FacingThreeBetRange(
                    callRange = Range(setOf("AQs", "AJs", "ATs", "A5s", "KQs", "KJs", "QQ", "JJ", "TT", "99", "88", "77")),
                    rcRange = Range(setOf( )),
                    fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo")),
                    fourBetBluffRange = Range(setOf("KTs"))
                )
            ),
            "CO" to mapOf(
                "UTG+1" to FacingThreeBetRange(
                    callRange = Range(setOf()),
                    rcRange = Range(setOf( )),
                    fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo")),
                    fourBetBluffRange = Range(setOf())

                ),
                "UTG+2" to FacingThreeBetRange(
                    callRange = Range(setOf()),
                    rcRange = Range(setOf( )),
                    fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo", "QQ")),
                    fourBetBluffRange = Range(setOf("A5s"))
                ),
                "LJ" to FacingThreeBetRange(
                    callRange = Range(setOf()),
                    rcRange = Range(setOf( )),
                    fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo", "QQ")),
                    fourBetBluffRange = Range(setOf())
                ),
                "HJ" to FacingThreeBetRange(
                    callRange = Range(setOf("AQs")),
                    rcRange = Range(setOf("JJ")),
                    fourBetValueRange = Range(setOf("AA", "KK", "QQ", "AKs", "AKo")),
                    fourBetBluffRange = Range(setOf("A5s", "A4s"))
                ),
                "BTN" to FacingThreeBetRange(
                    callRange = Range(setOf("AQs", "AJs", "KQs")),
                    rcRange = Range(setOf("ATs", "A5s", "TT", "99")),
                    fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ", "JJ")),
                    fourBetBluffRange = Range(setOf("AQo", "AJo", "KJs", "KTs", "K9s", "JTs"))
                ),
                "SB" to FacingThreeBetRange(
                    callRange = Range(setOf("AQs", "AJs", "KQs")),
                    rcRange = Range(setOf("ATs", "KJs", "KTs", "99")),
                    fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "AQo", "KK", "QQ", "JJ")),
                    fourBetBluffRange = Range(setOf("AJo"))
                ),
                "BB" to FacingThreeBetRange(
                    callRange = Range(setOf("AQs", "AJs", "ATs", "A5s", "KQs", "KJs", "TT", "99", "88")),
                    rcRange = Range(setOf("JJ")),
                    fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ")),
                    fourBetBluffRange = Range(setOf("AQo", "A8s", "KJo"))
                )
            ),
            "BTN" to mapOf(
                "UTG+1" to FacingThreeBetRange(
                    callRange = Range(setOf()),
                    rcRange = Range(setOf( )),
                    fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo","QQ")),
                    fourBetBluffRange = Range(setOf("AJs"))

                ),
                "UTG+2" to FacingThreeBetRange(
                    callRange = Range(setOf()),
                    rcRange = Range(setOf( )),
                    fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo", "QQ")),
                    fourBetBluffRange = Range(setOf("A5s"))
                ),
                "LJ" to FacingThreeBetRange(
                    callRange = Range(setOf()),
                    rcRange = Range(setOf( )),
                    fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo", "QQ")),
                    fourBetBluffRange = Range(setOf("A4s"))
                ),
                "HJ" to FacingThreeBetRange(
                    callRange = Range(setOf()),
                    rcRange = Range(setOf()),
                    fourBetValueRange = Range(setOf("AA", "KK", "QQ", "AKs", "AKo")),
                    fourBetBluffRange = Range(setOf("AQs", "76s"))
                ),
                "CO" to FacingThreeBetRange(
                    callRange = Range(setOf()),
                    rcRange = Range(setOf()),
                    fourBetValueRange = Range(setOf("AA", "AKs", "AKo","KK","QQ")),
                    fourBetBluffRange = Range(setOf("A5s"))
                ),
                "SB" to FacingThreeBetRange(
                    callRange = Range(setOf()),
                    rcRange = Range(setOf( )),
                    fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "KK", "QQ", "JJ", "TT")),
                    fourBetBluffRange = Range(setOf("AQo", "AJs", "ATs","KQs", "KJs", "KTs", "99"))
                ),
                "BB" to FacingThreeBetRange(
                    callRange = Range(setOf("AJs", "ATs", "A5s", "KQs", "KJs", "KTs", "QJs", "QTs", "JTs", "99", "88", "77", "66")),
                    rcRange = Range(setOf("AQs", "AQo", "AJo", "A8s", "A7s", "KQo", "TT" )),
                    fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ", "JJ")),
                    fourBetBluffRange = Range(setOf("ATo"))
                )
            ),
            "SB" to mapOf(
                "UTG+1" to FacingThreeBetRange(
                    callRange = Range(setOf()),
                    rcRange = Range(setOf( )),
                    fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "KK")),
                    fourBetBluffRange = Range(setOf("A5s"))

                ),
                "UTG+2" to FacingThreeBetRange(
                    callRange = Range(setOf()),
                    rcRange = Range(setOf( )),
                    fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo")),
                    fourBetBluffRange = Range(setOf("A5s"))
                ),
                "LJ" to FacingThreeBetRange(
                    callRange = Range(setOf()),
                    rcRange = Range(setOf( )),
                    fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo", "QQ")),
                    fourBetBluffRange = Range(setOf())
                ),
                "HJ" to FacingThreeBetRange(
                    callRange = Range(setOf()),
                    rcRange = Range(setOf()),
                    fourBetValueRange = Range(setOf("AA", "KK", "QQ", "AKs", "AKo")),
                    fourBetBluffRange = Range(setOf("A5s"))
                ),
                "CO" to FacingThreeBetRange(
                    callRange = Range(setOf()),
                    rcRange = Range(setOf()),
                    fourBetValueRange = Range(setOf("AA", "AKs", "AKo","KK","QQ")),
                    fourBetBluffRange = Range(setOf("A5s"))
                ),
                "BTN" to FacingThreeBetRange(
                    callRange = Range(setOf()),
                    rcRange = Range(setOf()),
                    fourBetValueRange = Range(setOf("AA", "AKs", "AKo","KK","QQ")),
                    fourBetBluffRange = Range(setOf("ATs"))
                ),
                "BB" to FacingThreeBetRange(
                    callRange = Range(setOf("AJs", "ATs", "A9s", "KQs", "KJs", "KTs", "QJs", "JTs", "99", "88", "77", "66")),
                    rcRange = Range(setOf("A7s", "A6s", "A5s", "K9s", "Q9s", "J9s", "T9s")),
                    fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "KK", "QQ", "JJ", "TT")),
                    fourBetBluffRange = Range(setOf("AQo", "AJo", "ATo", "A4s", "A3s", "KQo", "K5s"))
                )
            ),
        "BB" to mapOf(
            "UTG+1" to FacingThreeBetRange(
                callRange = Range(setOf()),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "KK")),
                fourBetBluffRange = Range(setOf("A5s"))

            ),
            "UTG+2" to FacingThreeBetRange(
                callRange = Range(setOf()),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("A5s"))
            ),
            "LJ" to FacingThreeBetRange(
                callRange = Range(setOf()),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "AKs", "AKo", "QQ")),
                fourBetBluffRange = Range(setOf("A5s"))
            ),
            "HJ" to FacingThreeBetRange(
                callRange = Range(setOf()),
                rcRange = Range(setOf()),
                fourBetValueRange = Range(setOf("AA", "KK", "QQ", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("A4s"))
            ),
            "CO" to FacingThreeBetRange(
                callRange = Range(setOf()),
                rcRange = Range(setOf()),
                fourBetValueRange = Range(setOf("AA", "AKs", "AKo","KK","QQ")),
                fourBetBluffRange = Range(setOf("A5s"))
            ),
            "BTN" to FacingThreeBetRange(
                callRange = Range(setOf()),
                rcRange = Range(setOf()),
                fourBetValueRange = Range(setOf("AA", "AKs", "AKo","KK")),
                fourBetBluffRange = Range(setOf("A5s"))
            ),
            "SB" to FacingThreeBetRange(
                callRange = Range(setOf()),
                rcRange = Range(setOf()),
                fourBetValueRange = Range(setOf("AA", "AKs", "AKo","KK", "QQ")),
                fourBetBluffRange = Range(setOf())
            )
        )

    )
}