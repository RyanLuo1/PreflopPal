package com.ryanluo.prefloppal.models

import com.ryanluo.prefloppal.utils.Range

object Six_Max_FacingThreeBetRanges {

    val RANGES = mapOf(

        "UTG" to mapOf(
            "MP" to FacingThreeBetRange(
                callRange = Range(setOf("JJ", "TT", "99", "88", "AKs", "AQs")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "AKo", "AKs", "QQ")),
                fourBetBluffRange = Range(setOf("AQo", "ATo"))
            ),
            "CO" to FacingThreeBetRange(
                callRange = Range(setOf("JJ", "TT", "99", "88", "AQs", "AJs", "ATs", "KQs", "KJs","QJs","JTs", "T9s")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "AKo", "AKs", "QQ")),
                fourBetBluffRange = Range(setOf("A9s", "A5s", "KTs", "QTs", "98s"))
            ),
            "BTN" to FacingThreeBetRange(
                callRange = Range(setOf("JJ", "TT", "99", "88", "AQs", "AJs", "ATs", "KQs", "KJs","QJs","JTs", "T9s")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "AKo", "AKs", "QQ")),
                fourBetBluffRange = Range(setOf("A9s", "A5s", "KTs", "QTs", "98s"))
            ),
            "SB" to FacingThreeBetRange(
                callRange = Range(setOf("JJ", "TT", "99", "88", "77", "66", "AQs", "AJs", "ATs", "KQs", "KJs","QJs","JTs", "T9s")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "AKo", "AKs", "QQ")),
                fourBetBluffRange = Range(setOf("A9s", "A5s", "KTs", "QTs", "98s"))
            ),
            "BB" to FacingThreeBetRange(
                callRange = Range(setOf("JJ", "TT", "99", "88", "77", "66", "AQs", "AJs", "ATs", "KQs", "KJs","QJs","JTs", "T9s")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "AKo", "AKs", "QQ")),
                fourBetBluffRange = Range(setOf("A9s", "A5s", "KTs", "QTs", "98s"))
            )
        ),
        "MP" to mapOf(
            "CO" to FacingThreeBetRange(
                callRange = Range(setOf("TT", "99", "88", "77", "66", "AQs", "AJs", "ATs", "KQs", "KJs", "KTs",
                    "QJs", "QTs", "JTs", "T9s", "98s", "AQo")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "AKo", "AKs", "QQ", "JJ")),
                fourBetBluffRange = Range(setOf("A9s","A8s","A5s", "A4s", "A3s", "A2s", "KQo", "AJo", "87s"))
            ),
            "BTN" to FacingThreeBetRange(
                callRange = Range(setOf("TT", "99", "88", "77", "66", "55", "AQs", "AJs", "ATs", "KQs", "KJs", "KTs",
                    "QJs", "QTs", "JTs", "T9s", "98s", "87s", "AQo")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "AKo", "AKs", "QQ", "JJ")),
                fourBetBluffRange = Range(setOf("A9s","A8s", "A7s", "A5s", "A4s", "A3s", "A2s", "KQo", "AJo", "76s"))
            ),
            "SB" to FacingThreeBetRange(
                callRange = Range(setOf("TT", "99", "88", "77", "66", "55", "AQs", "AJs", "ATs", "KQs", "KJs", "KTs",
                    "QJs", "QTs", "JTs", "J9s", "T9s", "98s", "87s", "76s", "AQo", "AJo")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "AKo", "AKs", "QQ", "JJ")),
                fourBetBluffRange = Range(setOf("A9s","A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s", "KJo", "ATo"))
            ),
            "BB" to FacingThreeBetRange(
                callRange = Range(setOf("TT", "99", "88", "77", "66", "55", "AQs", "AJs", "ATs", "KQs", "KJs", "KTs",
                    "QJs", "QTs", "JTs", "T9s", "98s", "87s", "76s", "AQo")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "AKo", "AKs", "QQ", "JJ")),
                fourBetBluffRange = Range(setOf("A9s","A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s", "AJo", "KQo"))
            )
        ),
        "CO" to mapOf(
            "MP" to FacingThreeBetRange(
                callRange = Range(setOf("JJ", "TT", "99", "88", "77", "AQs", "AJs", "ATs", "KQs", "KJs", "QJs")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "QQ", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("A5s", "A4s", "KTs", "QTs", "JTs"))
            ),
            "BTN" to FacingThreeBetRange(
                callRange = Range(setOf("99", "88", "77", "66", "55", "44", "AQs", "AJs", "ATs", "A9s", "A5s", "KQs", "KJs", "KTs",
                  "K9s", "QJs", "QTs", "Q9s", "JTs", "J9s", "T9s", "T8s", "98s", "87s", "76s", "65s", "AQo", "AJo", "KQo")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "QQ", "JJ", "TT", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("A8s", "A7s", "A6s", "A4s", "A3s", "A2s", "ATo", "KJo", "97s", "86s", "54s"))
            ),
            "SB" to FacingThreeBetRange(
                callRange = Range(setOf("99", "88", "77", "66", "55", "44", "AQs", "AJs", "ATs", "A9s", "A5s", "KQs", "KJs", "KTs",
                    "K9s", "QJs", "QTs", "Q9s", "JTs", "J9s", "T9s", "T8s", "98s", "87s", "76s", "65s", "AQo", "AJo", "KQo")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "QQ", "JJ", "TT", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("A8s", "A7s", "A6s", "A4s", "A3s", "A2s", "ATo", "KJo", "97s", "86s", "54s"))
            ),
            "BB" to FacingThreeBetRange(
                callRange = Range(setOf("99", "88", "77", "66", "55", "44", "AQs", "AJs", "ATs", "A9s", "A5s", "KQs", "KJs", "KTs",
                    "K9s", "QJs", "QTs", "Q9s", "JTs", "J9s", "T9s", "T8s", "98s", "87s", "76s", "65s", "AQo", "AJo", "KQo")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "QQ", "JJ", "TT", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("A8s", "A4s", "A3s", "A2s", "ATo", "KJo", "T8s", "97s", "65s", "54s"))
            ),
        ),
        "BTN" to mapOf(
            "MP" to FacingThreeBetRange(
                callRange = Range(setOf("JJ", "TT", "99", "88", "77", "AQs", "AJs", "ATs", "KQs", "KJs", "QJs", "JTs", "AQo")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "QQ", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("A5s", "A4s", "KTs", "QTs", "T9s", "98s"))
            ),
            "CO" to FacingThreeBetRange(
                callRange = Range(setOf("TT", "99", "88", "77", "66", "55", "AQs", "AJs", "ATs", "A9s", "A5s", "KQs", "KJs", "KTs",
                    "QJs", "QTs", "JTs", "T9s", "98s", "87s", "AQo", "AJo", "KQo")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "QQ", "JJ", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("A8s", "A7s", "A6s", "A4s", "A3s", "A2s", "K9s", "Q9s", "J9s", "76s", "65s"))
            ),
            "SB" to FacingThreeBetRange(
                callRange = Range(setOf("88", "77", "66", "55", "44", "33", "22", "ATs", "A9s", "A8s", "A7s", "A6s","A5s", "A4s", "A3s", "A2s",
                    "KQs", "KJs", "KTs", "K9s", "K8s", "K7s", "QJs", "QTs", "Q9s", "JTs", "J9s", "T9s", "T8s", "98s", "87s", "76s", "65s",
                    "AQo", "AJo", "ATo","KQo", "KJo", )),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "QQ", "JJ", "TT", "99", "AKs", "AQs", "AJs", "AKo", "AQo")),
                fourBetBluffRange = Range(setOf("A8o", "A5o", "A4o", "A3o", "K6s", "K5s", "K4s", "75s", "64s", "54s", "43s"))
            ),
            "BB" to FacingThreeBetRange(
                callRange = Range(setOf("TT", "99", "88", "77", "66", "55", "44", "33", "AQs", "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s",
                    "KQs", "KJs", "KTs", "K9s", "K8s", "K7s", "QJs", "QTs", "Q9s", "JTs", "J9s", "T9s", "98s", "87s", "76s", "65s", "54s",
                    "AQo", "AJo", "ATo", "KQo", "KJo", "QJo")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "QQ", "JJ", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("22", "K6s", "K5s", "Q8s", "J8s", "T8s", "97s", "86s", "75s", "64s", "53s", "43s"))
            )
        ),
        "SB" to mapOf(
            "MP" to FacingThreeBetRange(
                callRange = Range(setOf("JJ", "TT", "99", "88", "77", "AQs", "AJs", "ATs", "KQs", "KJs", "QJs", "JTs", "AQo")),
                fourBetValueRange = Range(setOf("AA", "KK", "QQ", "AKs", "AKo")),
                rcRange = Range(setOf( )),
                fourBetBluffRange = Range(setOf("A5s", "A4s", "KTs", "QTs", "T9s", "98s"))
            ),
            "CO" to FacingThreeBetRange(
                callRange = Range(setOf("TT", "99", "88", "77", "66", "AQs", "AJs", "ATs", "A9s", "KQs", "KJs", "KTs", "QJs", "QTs", "JTs", "AQo", "AJo")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "QQ", "JJ", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("A5s", "A4s", "K9s", "Q9s", "J9s", "T9s", "98s", "87s", "KQo"))
            ),
            "BTN" to FacingThreeBetRange(
                callRange = Range(setOf("TT", "99", "88", "77", "66", "55", "AQs", "AJs", "ATs", "A9s", "A5s", "KQs", "KJs", "KTs", "QJs", "QTs", "JTs", "T9s", "98s", "AQo", "AJo", "KQo")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "QQ", "JJ", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("A8s", "A7s", "A6s", "A4s", "A3s", "K9s", "Q9s", "J9s", "87s", "76s", "65s", "ATo"))
            ),
            "BB" to FacingThreeBetRange(
                callRange = Range(setOf("99", "88", "77", "AJs", "ATs", "KQs", "KJs", "KTs", "QJs")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "QQ", "JJ", "TT", "AKs", "AQs", "AKo", "AQo")),
                fourBetBluffRange = Range(setOf("A4s", "AJo", "ATo", "KQo"))
            )
        ),
        "BB" to mapOf(
            "MP" to FacingThreeBetRange(
                callRange = Range(setOf("JJ", "TT", "99", "88", "77", "66", "AQs", "AJs", "ATs", "A9s", "KQs", "KJs", "QJs", "JTs", "AQo", "AJo")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "QQ", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("A5s", "A4s", "KTs", "QTs", "T9s", "98s", "87s", "76s"))
            ),
            "CO" to FacingThreeBetRange(
                callRange = Range(setOf("JJ", "TT", "99", "88", "77", "66", "55", "AQs", "AJs", "ATs", "A9s", "A5s", "KQs", "KJs", "KTs", "QJs", "QTs", "JTs", "T9s", "98s", "AQo", "AJo", "KQo")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "QQ", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("A8s", "A7s", "A6s", "A4s", "K9s", "Q9s", "J9s", "87s", "76s", "65s", "ATo"))
            ),
            "BTN" to FacingThreeBetRange(
                callRange = Range(setOf("TT", "99", "88", "77", "66", "55", "44", "AQs", "AJs", "ATs", "A9s", "A8s", "A7s", "A5s", "KQs", "KJs", "KTs", "K9s", "QJs", "QTs", "Q9s", "JTs", "J9s", "T9s", "98s", "87s", "AQo", "AJo", "KQo", "QJo")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "QQ", "JJ", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("A6s", "A4s", "A3s", "K8s", "Q8s", "J8s", "T8s", "97s", "86s", "76s", "65s", "54s", "ATo"))
            ),
            "SB" to FacingThreeBetRange(
                callRange = Range(setOf("TT", "99", "88", "77", "66", "AQs", "AJs", "ATs", "KQs", "QJs", "JTs","AQo")),
                rcRange = Range(setOf( )),
                fourBetValueRange = Range(setOf("AA", "KK", "QQ", "JJ", "AKs", "AKo")),
                fourBetBluffRange = Range(setOf("A5s", "A4s", "A3s", "A2s", "T8s", "97s", "86s", "75s", "64s", "54s", "A9o"))
            )
        )

    )
}