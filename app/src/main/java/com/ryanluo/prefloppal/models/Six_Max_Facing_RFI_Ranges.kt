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
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AQo", "AJs", "ATs", "KK", "KQs", "QQ", "JJ", "TT", "99")),
                bluffRange = Range(setOf("A5s", "A4s", "KQo", "KJs", "KTs", "QJs",))
            )
        ),
        "CO" to mapOf(
            "UTG" to RFIThreeBetRange(
                callRange = Range(setOf( )),
                rcRange = Range(setOf("99", "88", "77")),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AQo", "AJs", "ATs", "KK", "KQs", "KJs", "QQ", "QJs", "JJ", "TT")),
                bluffRange = Range(setOf( "A5s", "A4s","KQo", "KTs", "QTs",))
            ),
            "MP" to RFIThreeBetRange(
                callRange = Range(setOf( )),
                rcRange = Range(setOf("77")),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AQo", "AJs", "KK", "KQs", "KQo", "KJs", "QQ", "QJs", "JJ", "TT", "99", "88")),
                bluffRange = Range(setOf("AJo", "ATs", "A9s", "A5s", "A4s", "KTs", "K9s", "QTs", "JTs"))
            )
        ),
        "BTN" to mapOf(
            "UTG" to RFIThreeBetRange(
                callRange = Range(setOf("QJs", "JTs", "T9s", "99", "88", "77", "66","65s", "55", "54s", "44", "33" )),
                rcRange = Range(setOf("AQs", "AQo", "AJs", "KQs", "KJs", "JJ", "TT" )),
                fcRange = Range(setOf("98s" )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ")),
                bluffRange = Range(setOf("AJo", "A9s", "A8s", "A7s", "A5s", "A4s", "A3s", "KQo", "K9s"))
            ),
            "MP" to RFIThreeBetRange(
                callRange = Range(setOf("ATs", "KTs", "QTs", "JTs", "T9s", "99", "88", "77", "66", "55", "44" )),
                rcRange = Range(setOf("AJs", "KQs", "QJs", "TT" )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AQo", "KK", "KQo", "KJs", "QQ")),
                bluffRange = Range(setOf("AJo", "A9s", "A8s", "A7s", "A5s", "A4s", "A3s", "K9s", "Q9s"))
            ),
            "CO" to RFIThreeBetRange(
                callRange = Range(setOf("T9s", "88", "77", "66" )),
                rcRange = Range(setOf("AJo", "ATs", "KTs", "QTs", "JTs", "99")),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AQo", "AJs", "KK", "KQs", "KQo", "KJs", "QQ", "QJs", "JJ", "TT")),
                bluffRange = Range(setOf("ATo", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "K9s", "K8s", "Q9s", "J9s"))
            )
        ),
        "SB" to mapOf(
            "UTG" to RFIThreeBetRange(
                callRange = Range(setOf("A9s", "A8s", "QTs", "JTs", "T9s", "99", "88", "77", "66", "55", "44", "33", "22" )),
                rcRange = Range(setOf("AQo", "AJs", "ATs", "KTs", "TT")),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "KK", "KQs", "QQ", "JJ")),
                bluffRange = Range(setOf("A5s", "A4s", "A3s"))
            ),
            "MP" to RFIThreeBetRange(
                callRange = Range(setOf("ATs", "A9s", "A8s", "A7s", "KTs", "QTs", "JTs", "T9s", "99", "88", "77", "66", "55", "44" )),
                rcRange = Range(setOf("AQo", "KQo", "KJs", "QJs", "TT" )),
                fcRange = Range(setOf("A6s", "33", "22")),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AJs", "KK", "KQs", "QQ", "JJ")),
                bluffRange = Range(setOf("AJo", "A5s", "A4s", "A3s", "K9s"))
            ),
            "CO" to RFIThreeBetRange(
                callRange = Range(setOf("77")),
                rcRange = Range(setOf("AJo", "JTs", "88" )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AQo", "AJs", "ATs", "KK", "KQs", "KQo", "KJs", "KTs", "QQ", "QJs", "QTs", "JJ", "TT", "99")),
                bluffRange = Range(setOf("A9s", "A8s", "A5s", "A4s", "K9s", "J9s", "T9s"))
            ),
            "BTN" to RFIThreeBetRange(
                callRange = Range(setOf( )),
                rcRange = Range(setOf("A9s", "A8s", "A7s", "K9s", "Q9s", "J9s", "T9s")),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AQo", "AJs", "ATs", "KK", "KQs", "KQo", "KJs", "KTs", "QQ", "QJs", "QTs", "JJ", "TT", "99", "88", "77")),
                bluffRange = Range(setOf("AJo", "ATo", "A5s", "A4s", "KJo"))
            )
        ),
        "BB" to mapOf(
            "UTG" to RFIThreeBetRange(
                callRange = Range(setOf("AQo", "AJs", "AJo", "ATs", "ATo", "A9s", "A9o", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s", "KQs", "KQo", "KJs", "KJo", "KTs", "KTo", "K9s", "K8s", "K7s", "K6s", "K5s", "K4s", "K3s", "K2s", "QJo", "QTs", "QTo", "Q9s", "Q8s", "Q7s", "Q6s", "Q5s", "Q4s", "Q3s", "Q2s", "JJ", "JTs", "JTo", "J9s", "J8s", "J7s", "J6s", "J5s", "J4s", "J3s", "TT", "T9s", "T9o", "T8s", "T7s", "T6s", "99", "98s", "97s", "96s", "95s", "88", "87o", "86s", "85s", "77", "75s", "74s", "66", "64s", "63s", "55", "54s", "53s", "52s", "44", "43s", "42s", "33", "32s", "22" )),
                rcRange = Range(setOf("AQs" )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ")),
                bluffRange = Range(setOf("QJs", "87s", "76s", "65s"))
            ),
            "MP" to RFIThreeBetRange(
                callRange = Range(setOf("AQo", "AJs", "AJo", "ATs", "ATo", "A9s", "A9o", "A8s", "A8o", "A7s", "A7o", "A6s", "A5s", "A5o", "A4s", "A4o", "A3s", "A2s", "KQs", "KQo", "KJs", "KJo", "KTs", "KTo", "K9s", "K9o", "K8s", "K7s", "K6s", "K5s", "K4s", "K3s", "K2s", "QTs", "Q9s", "Q8s", "Q7s", "Q6s", "Q5s", "Q4s", "Q3s", "Q2s", "JJ", "JTs", "JTo", "J9s", "J8s", "J7s", "J6s", "J5s", "J4s", "J3s", "J2s", "TT", "T9s", "T8s", "T7s", "T6s", "T5s", "99", "98s", "98o", "97s", "96s", "95s", "88", "87o", "86s", "85s", "84s", "77", "75s", "74s", "66", "64s", "63s", "55", "53s", "52s", "44", "43s", "42s", "33", "32s", "22" )),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf("Q9o", "J9o", "76o", "65o")),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "KK", "QQ")),
                bluffRange = Range(setOf("QJs", "87s", "76s", "65s", "54s"))
            ),
            "CO" to RFIThreeBetRange(
                callRange = Range(setOf("AJs", "AJo", "ATs", "ATo", "A9s", "A9o", "A8s", "A8o", "A7s", "A6s", "A5o", "A4s", "A3s", "A2s", "KQs", "KJo", "KTs", "KTo", "K9s", "K8s", "K7s", "K6s", "K5s", "K4s", "K3s", "K2s", "QJs", "QJo", "QTs", "QTo", "Q9s", "Q8s", "Q7s", "Q6s", "Q5s", "Q4s", "Q3s", "Q2s", "JTo", "J9s", "J8s", "J7s", "J6s", "J5s", "J4s", "T9s", "T8s", "T7s", "T6s", "99", "98s", "97s", "96s", "88", "87s", "86s", "85s", "77", "76s", "75s", "74s", "66", "64s", "63s", "55", "54s", "53s", "52s", "44", "43s", "33", "22" )),
                rcRange = Range(setOf("AQo", "KJs", "TT",)),
                fcRange = Range(setOf("K9o", "T9o", "95s", "42s" )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "KK", "QQ", "JJ")),
                bluffRange = Range(setOf("A5s", "JTs", "65s"))
            ),
            "BTN" to RFIThreeBetRange(
                callRange = Range(setOf("AJo", "ATs", "ATo", "A9s", "A9o", "A8s", "A8o", "A7s", "A7o", "A6s", "A5o", "A4s", "A3s", "A2s", "KQo", "KJo", "KTo", "K9s", "K9o", "K8s", "K7s", "K6s", "K5s", "K4s", "K3s", "K2s", "QJs", "QJo", "QTs", "QTo", "Q9s", "Q9o", "Q8s", "Q7s", "Q6s", "Q5s", "Q4s", "Q3s", "Q2s", "JTo", "J9o", "J8s", "J7s", "J6s", "J5s", "J4s", "J3s", "J2s", "T7s", "T6s", "97s", "96s", "88", "86s", "85s", "77", "75s", "74s", "66", "65s", "64s", "63s", "55", "54s", "53s", "52s", "44", "43s", "42s", "33", "22" )),
                rcRange = Range(setOf("KJs", "KTs", "T8s", "98s", "87s", "76s" )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AQo", "AJs", "KK", "KQs", "QQ", "JJ", "TT", "99")),
                bluffRange = Range(setOf("A5s", "JTs", "J9s", "T9s"))
            ),
            "SB" to RFIThreeBetRange(
                callRange = Range(setOf("AJo", "ATs", "ATo", "A9s", "A9o", "A8s", "A8o", "A7s", "A7o", "A6s", "A6o", "A5o", "A4o", "A3s", "A3o", "A2s", "KQo", "KJo", "KTo", "K9s", "K9o", "K8s", "K8o", "K7s", "K7o", "K6s", "K6o", "K5s", "K4s", "K3s", "K2s", "QJs", "QJo", "QTo", "Q9s", "Q9o", "Q8s", "Q8o", "Q7s", "Q6s", "Q5s", "Q4s", "Q3s", "Q2s", "JTo", "J9s", "J9o", "J8s", "J8o", "J7s", "J6s", "J5s", "J4s", "J3s", "J2s", "T9o", "T8s", "T8o", "T7s", "T6s", "T5s", "T4s", "T3s", "T2s", "99", "98o", "97s", "96s", "95s", "88", "87o", "86s", "85s", "84s", "77", "76o", "75s", "74s", "73s", "66", "64s", "63s", "55", "53s", "52s", "44", "43s", "42s", "33", "32s", "22" )),
                rcRange = Range(setOf("KJs", "KTs", "QTs", "JTs" )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AQo", "AJs", "KK", "KQs", "QQ", "JJ", "TT")),
                bluffRange = Range(setOf("A5s", "A4s", "A2o", "T9s", "98s", "87s", "76s", "65s", "54s"))
            )
        )
    )
}