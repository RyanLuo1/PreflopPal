package com.ryanluo.prefloppal.models

import com.ryanluo.prefloppal.utils.Range

object Six_Max_FacingThreeBetRanges {

    val RANGES = mapOf(

        "UTG" to mapOf(
            "MP" to FacingThreeBetRange(
                callRange = Range(setOf("KJs", "99", "88")),
                rcRange = Range(setOf("AQs", "JJ", "TT")),
                fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ")),
                fourBetBluffRange = Range(setOf("AJs","A5s", "KQs",))
            ),
            "CO" to FacingThreeBetRange(
                callRange = Range(setOf("ATs", "KJs", "KTs", "JTs", "99", "88", "77", "66")),
                rcRange = Range(setOf("AQs", "JJ", "TT" )),
                fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ")),
                fourBetBluffRange = Range(setOf("AJs", "A5s", "KQs"))
            ),
            "BTN" to FacingThreeBetRange(
                callRange = Range(setOf("AQs", "AJs", "KQs", "99", "88")),
                rcRange = Range(setOf("ATs", "KJs", "JJ", "TT")),
                fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ")),
                fourBetBluffRange = Range(setOf("AQo"))
            ),
            "SB" to FacingThreeBetRange(
                callRange = Range(setOf("AQs", "AJs", "ATs", "KQs", "KJs", "QQ", "JJ", "TT", "99", "88")),
                rcRange = Range(setOf("AKo" )),
                fourBetValueRange = Range(setOf("AA", "AKs", "KK")),
                fourBetBluffRange = Range(setOf())
            ),
            "BB" to FacingThreeBetRange(
                callRange = Range(setOf("AQs", "AJs", "ATs", "KQs", "KJs", "QQ", "JJ", "TT", "99")),
                rcRange = Range(setOf("AKo")),
                fourBetValueRange = Range(setOf("AA", "AKs", "KK")),
                fourBetBluffRange = Range(setOf())
            )
        ),
        "MP" to mapOf(
            "CO" to FacingThreeBetRange(
                callRange = Range(setOf("AJs", "A5s", "KTs", "JTs", "99", "88", "77", "66")),
                rcRange = Range(setOf("AQs", "AQo", "KQs", "KJs", "TT" )),
                fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ", "JJ")),
                fourBetBluffRange = Range(setOf())
            ),
            "BTN" to FacingThreeBetRange(
                callRange = Range(setOf("AQs", "AJs", "KQs", "99", "88", "77")),
                rcRange = Range(setOf("TT")),
                fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "AQo", "KK", "QQ", "JJ")),
                fourBetBluffRange = Range(setOf("ATs","KJs", "KTs"))
            ),
            "SB" to FacingThreeBetRange(
                callRange = Range(setOf()),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "KK", "QQ", "JJ")),
                fourBetBluffRange = Range(setOf("KQs"))
            ),
            "BB" to FacingThreeBetRange(
                callRange = Range(setOf("AQs", "AJs", "ATs", "A5s", "KQs", "KJs", "KTs", "QQ", "QJs", "JJ", "TT", "99")),
                rcRange = Range(setOf()),
                fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "KK")),
                fourBetBluffRange = Range(setOf())
            )
        ),
        "CO" to mapOf(
            "MP" to FacingThreeBetRange(
                callRange = Range(setOf()),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "KK", "QQ")),
                fourBetBluffRange = Range(setOf("A5s"))
            ),
            "BTN" to FacingThreeBetRange(
                callRange = Range(setOf("AQs", "AJs", "ATs", "A9s","A8s", "A5s", "A4s", "KQs", "K9s", "QJs", "QTs", "JTs", "T9s", "99", "98s", "88", "87s", "77", "76s", "66", "65s", "55", "44", "33", "22")),
                rcRange = Range(setOf("TT")),
                fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ", "JJ")),
                fourBetBluffRange = Range(setOf("AQo", "KJs", "KTs"))
            ),
            "SB" to FacingThreeBetRange(
                callRange = Range(setOf("AQs", "AQo", "AJs", "ATs", "A9s", "A5s", "KQs", "KJs", "KTs", "QJs", "QTs", "JJ", "JTs", "TT", "T9s", "99", "88", "77")),
                rcRange = Range(setOf("AKo")),
                fourBetValueRange = Range(setOf("AA", "AKs", "KK", "QQ")),
                fourBetBluffRange = Range(setOf())
            ),
            "BB" to FacingThreeBetRange(
                callRange = Range(setOf("AQs", "AJs", "ATs", "A5s", "A4s", "KQs", "KJs", "KTs", "QJs", "JTs", "TT", "99", "88")),
                rcRange = Range(setOf("AQo", "JJ")),
                fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ")),
                fourBetBluffRange = Range(setOf())
            ),
        ),
        "BTN" to mapOf(
            "MP" to FacingThreeBetRange(
                callRange = Range(setOf()),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "KK", "QQ", "JJ")),
                fourBetBluffRange = Range(setOf("AJs", "A5s", "KQs"))
            ),
            "CO" to FacingThreeBetRange(
                callRange = Range(setOf()),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "KK", "QQ", "JJ")),
                fourBetBluffRange = Range(setOf("AJs", "A5s", "KQs"))
            ),
            "SB" to FacingThreeBetRange(
                callRange = Range(setOf()),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "KK", "KQs", "QQ", "JJ", "TT", "99")),
                fourBetBluffRange = Range(setOf("AQo", "AJs", "ATs", "KJs"))
            ),
            "BB" to FacingThreeBetRange(
                callRange = Range(setOf("AQs", "AQo", "AJs", "AJo", "ATs", "A9s", "A8s", "A7s", "A5s", "A4s", "A3s", "KQs", "KQo", "KJs", "KTs", "K9s", "K6s", "QJs", "QTs", "JTs", "J9s", "T9s", "T8s", "99", "88", "77", "76s", "66")),
                rcRange = Range(setOf("TT")),
                fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ", "JJ")),
                fourBetBluffRange = Range(setOf())
            ),
        ),
        "SB" to mapOf(
            "MP" to FacingThreeBetRange(
                callRange = Range(setOf()),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ")),
                fourBetBluffRange = Range(setOf("AQs"))
            ),
            "CO" to FacingThreeBetRange(
                callRange = Range(setOf()),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ")),
                fourBetBluffRange = Range(setOf("AQs"))
            ),
            "BTN" to FacingThreeBetRange(
                callRange = Range(setOf()),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ")),
                fourBetBluffRange = Range(setOf("AQs"))
            ),
            "BB" to FacingThreeBetRange(
                callRange = Range(setOf("AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A3s", "A2s", "KJs", "KTs", "K9s", "K8s", "QJs", "QTs", "Q9s", "JTs", "J9s", "T9s", "T8s", "99", "98s", "88", "77", "66", "55")),
                rcRange = Range(setOf("AQo","KQs")),
                fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "KK", "QQ", "JJ", "TT")),
                fourBetBluffRange = Range(setOf("AJo", "ATo", "A4s", "KQo"))
            ),
        ),
        "BB" to mapOf(
            "MP" to FacingThreeBetRange(
                callRange = Range(setOf()),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ")),
                fourBetBluffRange = Range(setOf("AQs"))
            ),
            "CO" to FacingThreeBetRange(
                callRange = Range(setOf()),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "KK", "QQ")),
                fourBetBluffRange = Range(setOf("A5s", "KQs"))
            ),
            "BTN" to FacingThreeBetRange(
                callRange = Range(setOf()),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "KK", "QQ")),
                fourBetBluffRange = Range(setOf())
            ),
            "SB" to FacingThreeBetRange(
                callRange = Range(setOf()),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ")),
                fourBetBluffRange = Range(setOf("AQs"))
            ),
        )
    )
}