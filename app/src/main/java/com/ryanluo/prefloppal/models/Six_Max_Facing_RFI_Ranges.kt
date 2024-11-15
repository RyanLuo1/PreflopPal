package com.ryanluo.prefloppal.models

import com.ryanluo.prefloppal.utils.Range

object Six_Max_RFIThreeBetRanges {

    val RANGES = mapOf(

        //UTG can't face RFI

        "MP" to mapOf(
            "UTG" to RFIThreeBetRange(
                callRange = Range(setOf( )),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AQo", "AJs", "ATs", "KK", "KQs", "KJs", "KTs", "QQ", "QJs", "JJ", "TT", "99")),
                bluffRange = Range(setOf("A5s", "A4s",))
            )
        ),
        "CO" to mapOf(
            "UTG" to RFIThreeBetRange(
                callRange = Range(setOf( )),
                rcRange = Range(setOf("99", "88", "77")),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AQo", "AJs", "ATs", "KK", "KQs", "KJs", "KTs", "QQ", "QJs", "QTs", "JJ", "TT")),
                bluffRange = Range(setOf( "A5s", "A4s","KQo"))
            ),
            "MP" to RFIThreeBetRange(
                callRange = Range(setOf( )),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AQo", "AJs", "ATs", "KK", "KQs", "KJs", "KTs", "QQ", "QJs", "QTs", "JJ", "JTs", "TT", "99", "88", "77")),
                bluffRange = Range(setOf("AJo", "A9s", "A5s", "A4s", "A3s","KQo", "K9s"))
            )
        ),
        "BTN" to mapOf(
            "UTG" to RFIThreeBetRange(
                callRange = Range(setOf( )),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "KK", "QQ", "JJ", "AKs", "AKo")),
                bluffRange = Range(setOf("A3s", "A2s", "K5s", "K4s"))
            ),
            "MP" to RFIThreeBetRange(
                callRange = Range(setOf( )),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "KK", "QQ", "JJ", "AKs", "AKo")),
                bluffRange = Range(setOf("A3s", "A2s", "K5s", "K4s"))
            ),
            "CO" to RFIThreeBetRange(
                callRange = Range(setOf( )),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "KK", "QQ", "JJ", "TT", "AKs", "AQs", "AKo")),
                bluffRange = Range(setOf("A2s", "K4s", "K3s", "Q5s"))
            )
        ),
        "SB" to mapOf(
            "UTG" to RFIThreeBetRange(
                callRange = Range(setOf( )),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "KK", "QQ", "JJ", "AKs", "AKo")),
                bluffRange = Range(setOf("A4s", "A3s", "K5s", "K4s"))
            ),
            "MP" to RFIThreeBetRange(
                callRange = Range(setOf( )),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "KK", "QQ", "JJ", "AKs", "AKo")),
                bluffRange = Range(setOf("A4s", "A3s", "K5s", "K4s"))
            ),
            "CO" to RFIThreeBetRange(
                callRange = Range(setOf( )),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "KK", "QQ", "JJ", "TT", "AKs", "AQs", "AKo")),
                bluffRange = Range(setOf("A3s", "A2s", "K4s", "K3s", "Q5s"))
            ),
            "BTN" to RFIThreeBetRange(
                callRange = Range(setOf( )),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "KK", "QQ", "JJ", "TT", "99", "AKs", "AQs", "AJs", "AKo", "AQo")),
                bluffRange = Range(setOf("A2s", "K3s", "K2s", "Q4s", "Q3s", "J4s", "T4s"))
            )
        ),
        "BB" to mapOf(
            "UTG" to RFIThreeBetRange(
                callRange = Range(setOf( )),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "KK", "QQ", "JJ", "AKs", "AKo")),
                bluffRange = Range(setOf("A5s", "A4s", "A3s", "K5s", "K4s", "Q5s"))
            ),
            "MP" to RFIThreeBetRange(
                callRange = Range(setOf( )),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "KK", "QQ", "JJ", "TT", "AKs", "AQs", "AKo")),
                bluffRange = Range(setOf("A4s", "A3s", "K4s", "K3s", "Q5s", "J5s"))
            ),
            "CO" to RFIThreeBetRange(
                callRange = Range(setOf( )),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "KK", "QQ", "JJ", "TT", "99", "AKs", "AQs", "AJs", "AKo", "AQo")),
                bluffRange = Range(setOf("A3s", "A2s", "K3s", "K2s", "Q4s", "J4s", "T4s"))
            ),
            "BTN" to RFIThreeBetRange(
                callRange = Range(setOf( )),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "KK", "QQ", "JJ", "TT", "99", "88", "AKs", "AQs", "AJs", "ATs", "KQs", "AKo", "AQo", "AJo")),
                bluffRange = Range(setOf("A2s", "K2s", "Q3s", "J3s", "T3s", "93s", "83s", "73s", "63s"))
            ),
            "SB" to RFIThreeBetRange(
                callRange = Range(setOf( )),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "KK", "QQ", "JJ", "TT", "99", "88", "77", "AKs", "AQs", "AJs", "ATs", "A9s", "KQs", "KJs", "AKo", "AQo", "AJo", "ATo")),
                bluffRange = Range(setOf("A2s", "K2s", "Q2s", "J2s", "T2s", "92s", "82s", "72s", "62s", "52s", "42s", "32s"))
            )
        )
    )
}