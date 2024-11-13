package com.ryanluo.prefloppal.models

import com.ryanluo.prefloppal.utils.Range

object Nine_Max_RFIThreeBetRanges {

    val RANGES = mapOf(

        //UTG can't face RFI

        "UTG+1" to mapOf(
            "UTG" to RFIThreeBetRange(
                callRange = Range(setOf("JJ", "TT")),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "A5s", "KK", "KQs", "KJs", "KTs", "QQ")),
                bluffRange = Range(setOf("AJs", "ATs", "A8s", "A7s", "A4s", "K5s"))
            )
        ),
        "UTG+2" to mapOf(
            "UTG" to RFIThreeBetRange(
                callRange = Range(setOf("JJ", "TT" )),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AJs", "A5s", "A4s", "KK", "KQs", "KJs", "KTs", "QQ")),
                bluffRange = Range(setOf("ATs", "A8s", "A7s", "A4s", "K5s"))
            ),
            "UTG+1" to RFIThreeBetRange(
                callRange = Range(setOf("JJ", "TT" )),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AJs", "A5s", "KK", "KQs", "KJs", "KTs", "QQ")),
                bluffRange = Range(setOf("AQo", "ATs", "A4s"))
            )
        ),
        "LJ" to mapOf(
            "UTG" to RFIThreeBetRange(
                callRange = Range(setOf("JJ", "TT")),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AJs","A5s", "KK", "KQs", "KJs", "KTs", "QQ")),
                bluffRange = Range(setOf("ATs", "A8s", "A7s", "A4s", "K5s"))
            ),
            "UTG+1" to RFIThreeBetRange(
                callRange = Range(setOf("JJ", "TT" )),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AJs", "ATs", "A5s", "A4s", "KK", "KQs", "KJs", "KTs", "QQ")),
                bluffRange = Range(setOf("AQo", "K5s"))
            ),
            "UTG+2" to RFIThreeBetRange(
                callRange = Range(setOf("99")),
                rcRange = Range(setOf("TT" )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AQo", "AJs", "ATs", "A5s", "A4s", "KK", "KQs", "KJs", "KTs", "QQ", "JJ")),
                bluffRange = Range(setOf("KQo", "K5s"))
            )
        ),
        "HJ" to mapOf(
            "UTG" to RFIThreeBetRange(
                callRange = Range(setOf("JJ", "TT")),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AJs", "ATs", "A5s", "A4s", "KK", "KQs", "KJs", "KTs", "QQ")),
                bluffRange = Range(setOf("AQo", "A8s", "A7s", "K6s", "K5s"))
            ),
            "UTG+1" to RFIThreeBetRange(
                callRange = Range(setOf("TT" )),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AJs", "ATs", "A5s", "A4s", "KK", "KQs", "KJs", "KTs", "QQ", "JJ")),
                bluffRange = Range(setOf("AQo", "K5s"))
            ),
            "UTG+2" to RFIThreeBetRange(
                callRange = Range(setOf("TT")),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AQo", "AJs", "ATs", "A5s", "A4s", "KK", "KQs", "KJs", "KTs", "QQ", "JJ")),
                bluffRange = Range(setOf("KQo", "K5s"))
            ),
            "LJ" to RFIThreeBetRange(
                callRange = Range(setOf( )),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AQo", "AJs", "ATs", "A5s", "A4s", "KK", "KQs", "KQo", "KJs", "KTs", "QQ", "JJ", "TT")),
                bluffRange = Range(setOf("A3s", "99"))
            )
        ),
        "CO" to mapOf(
            "UTG" to RFIThreeBetRange(
                callRange = Range(setOf( )),
                rcRange = Range(setOf("JJ", "TT" )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "ATs", "A5s", "A4s", "KK", "KQs", "KJs", "KTs", "QQ")),
                bluffRange = Range(setOf("A7s", "K6s", "K5s"))
            ),
            "UTG+1" to RFIThreeBetRange(
                callRange = Range(setOf("TT")),
                rcRange = Range(setOf()),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "ATs", "A5s", "A4s", "KK", "KQs", "KJs", "KTs", "QQ", "JJ")),
                bluffRange = Range(setOf("AQo", "AJs", "A3s", "K5s"))
            ),
            "UTG+2" to RFIThreeBetRange(
                callRange = Range(setOf("TT")),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AQo", "AJs", "ATs", "A5s", "A4s", "KK", "KQs", "KJs", "KTs", "QQ", "JJ")),
                bluffRange = Range(setOf("KQo", "K5s", "65s"))
            ),
            "LJ" to RFIThreeBetRange(
                callRange = Range(setOf("99", "88","77" )),
                rcRange = Range(setOf("JJ", "TT" )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AQo", "AJs", "ATs", "A5s", "A4s", "A3s", "KK", "KQs", "KQo", "KJs", "KTs", "QQ")),
                bluffRange = Range(setOf("AJo", "A8s"))
            ),
            "HJ" to RFIThreeBetRange(
                callRange = Range(setOf("99", "88", "77", "66")),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AQo", "AJs", "AJo", "ATs", "A9s", "A5s", "A4s", "A3s", "KK", "KQs", "KQo", "KJs", "KTs", "QQ", "QJs", "QTs", "JJ", "TT")),
                bluffRange = Range(setOf("A8s", "A7s"))
            )
        ),
        "BTN" to mapOf(
            "UTG" to RFIThreeBetRange(
                callRange = Range(setOf("AQs", "AJs", "ATs", "KQs", "JTs", "TT", "99", "88", "77", "54s" )),
                rcRange = Range(setOf("AQo", "A8s", "A5s", "A4s", "A3s", "KJs", "KTs", "QQ", "JJ" )),
                fcRange = Range(setOf("QJs", "QTs", "T9s", "66", "65s" )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "KK")),
                bluffRange = Range(setOf("K9s", "K6s", "K5s"))
            ),
            "UTG+1" to RFIThreeBetRange(
                callRange = Range(setOf("AQs", "AJs", "ATs","KQs", "99", "88", "77", "54s" )),
                rcRange = Range(setOf("AQo", "A8s", "A5s", "A4s", "A3s", "KTs", "QQ", "JJ", "JTs", "TT" )),
                fcRange = Range(setOf("98s", "66", "65s" )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "KK")),
                bluffRange = Range(setOf("A7s", "KQo", "K9s", "K6s", "K5s"))
            ),
            "UTG+2" to RFIThreeBetRange(
                callRange = Range(setOf("AJs", "ATs", "A9s", "KQs", "QTs", "99", "88", "77", "66", "54s")),
                rcRange = Range(setOf("AQs", "AQo", "A5s", "A4s", "A3s", "QQ", "QJs", "JJ", "TT" )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ")),
                bluffRange = Range(setOf("K9s", "K6s", "K5s"))
            ),
            "LJ" to RFIThreeBetRange(
                callRange = Range(setOf("99", "88", "77" )),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AQo", "AJs", "ATs", "A5s", "A4s", "KK", "KQs", "KQo", "KJs", "KTs", "QQ", "JJ", "TT")),
                bluffRange = Range(setOf("AJo"))
            ),
            "HJ" to RFIThreeBetRange(
                callRange = Range(setOf("99", "88", "77")),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AQo", "AJs", "AJo", "ATs", "A9s", "A5s", "A4s", "A3s", "KK", "KQs", "KQo", "KJs", "KTs", "QQ", "QJs", "QTs", "JJ", "TT")),
                bluffRange = Range(setOf("A8s", "A7s"))
            ),
            "CO" to RFIThreeBetRange(
                callRange = Range(setOf("ATs", "77")),
                rcRange = Range(setOf("AJs", "KJs", "KTs", "QJs", "QTs", "JTs", "TT", "99", "88" )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AQo", "AJo", "ATo", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "KK", "KQo", "KJo", "K9s", "QQ", "JJ")),
                bluffRange = Range(setOf("K6s", "Q9s"))
            )
        ),
        "SB" to mapOf(
            "UTG" to RFIThreeBetRange(
                callRange = Range(setOf("AQs", "AJs", "ATs", "JTs", "TT", "99", "88" )),
                rcRange = Range(setOf("A5s", "A3s", "KQs", "KJs", "KTs", "QQ", "JJ" )),
                fcRange = Range(setOf("77", "66", "65s", "54s")),
                valueRange = Range(setOf("AA", "AKs", "AKo", "A4s", "KK")),
                bluffRange = Range(setOf("AQo", "K6s", "K5s"))
            ),
            "UTG+1" to RFIThreeBetRange(
                callRange = Range(setOf("AQs", "AJs", "ATs", "JTs", "99", "88", "77" )),
                rcRange = Range(setOf("AQo", "A5s", "A4s", "KQs", "KJs", "KTs", "QQ", "JJ", "TT" )),
                fcRange = Range(setOf("QJs", "QTs", "T9s", "66", "54s" )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "KK")),
                bluffRange = Range(setOf("K6s", "K5s"))
            ),
            "UTG+2" to RFIThreeBetRange(
                callRange = Range(setOf("AQs", "AJs", "ATs", "JJ", "JTs", "TT", "99", "88", "77", "66" )),
                rcRange = Range(setOf("AQo", "A5s", "A3s", "KQs", "KJs", "KTs", "QQ", "QJs")),
                fcRange = Range(setOf("A9s","QTs")),
                valueRange = Range(setOf("AA", "AKs", "AKo", "A4s", "KK")),
                bluffRange = Range(setOf("K6s"))
            ),
            "LJ" to RFIThreeBetRange(
                callRange = Range(setOf("AJs", "ATs", "A9s", "99", "88", "77", "66", "55" )),
                rcRange = Range(setOf("AQo", "A5s", "QJs", "JJ", "JTs", "TT" )),
                fcRange = Range(setOf("44", "33", "22" )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "A4s", "A3s", "KK", "KTs", "QQ")),
                bluffRange = Range(setOf("KQo"))
            ),
            "HJ" to RFIThreeBetRange(
                callRange = Range(setOf("AJs", "ATs", "A9s", "A8s", "KQs", "KJs", "KTs", "TT", "99", "88", "77", "66", "55" )),
                rcRange = Range(setOf("AJo", "A5s", "QJs", "QTs" )),
                fcRange = Range(setOf("44", "33")),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "A4s","A3s", "KK", "KQo", "QQ")),
                bluffRange = Range(setOf("K6s"))
            ),
            "CO" to RFIThreeBetRange(
                callRange = Range(setOf("88", "77" )),
                rcRange = Range(setOf("AJs", "ATs", "KQs", "KJs", "99" )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AQo", "AJo", "A9s", "A5s", "A4s", "A3s", "KK", "KQo", "KJo", "KTs", "QQ", "QJs", "QTs", "JJ", "JTs", "TT")),
                bluffRange = Range(setOf("K9s"))
            ),
            "BTN" to RFIThreeBetRange(
                callRange = Range(setOf( )),
                rcRange = Range(setOf( )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AQo", "AJs", "AJo", "ATs", "ATo", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "KK", "KQs", "KQo", "KJs", "KJo", "KTs", "K9s", "QQ", "QJs", "QTs", "Q9s", "JJ", "JTs", "TT", "99", "88")),
                bluffRange = Range(setOf("A2s", "KTo", "T9s", "77"))
            )
        ),
        "BB" to mapOf(
            "UTG" to RFIThreeBetRange(
                callRange = Range(setOf("AQs", "AQo", "AJs", "AJo", "ATs", "ATo", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s", "KQs", "KQo", "KJs", "KTs", "K9s", "K8s", "K7s", "QQ", "QJs", "QTs", "Q9s", "Q8s", "Q6s", "JJ", "JTs", "JTo", "J9s", "J8s", "J7s", "TT", "T9s", "T8s", "T7s", "T6s", "99", "98s", "97s", "96s", "88", "87s", "86s", "85s", "77", "76s", "75s", "74s", "66", "65s", "64s", "63s", "55", "54s", "53s", "52s", "44", "43s", "42s", "33", "32s", "22" )),
                rcRange = Range(setOf("AKo", "K6s", "K5s", "K4s", "K3s", "K2s")),
                fcRange = Range(setOf("KJo", "J5s")),
                valueRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf())
            ),
            "UTG+1" to RFIThreeBetRange(
                callRange = Range(setOf("AQs", "AQo", "AJs", "AJo", "ATs", "ATo", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s", "KQs", "KQo", "KJs", "KTs", "K9s", "K8s", "K7s", "QQ", "QJs", "QTs", "Q9s", "Q8s", "Q6s", "JJ", "JTs", "JTo", "J9s", "J8s", "J7s", "TT", "T9s", "T8s", "T7s", "T6s", "99", "98s", "97s", "96s", "88", "87s", "86s", "85s", "77", "76s", "75s", "74s", "66", "65s", "64s", "63s", "55", "54s", "53s", "52s", "44", "43s", "42s", "33", "32s", "22" )),
                rcRange = Range(setOf("AKo", "K6s", "K5s", "K4s", "K3s", "K2s")),
                fcRange = Range(setOf("KJo", "KTo", "QTo")),
                valueRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf())
            ),
            "UTG+2" to RFIThreeBetRange(
                callRange = Range(setOf("AQs", "AQo", "AJs", "AJo", "ATs", "ATo", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s", "KQs", "KQo", "KJs", "KJo", "KTs", "K9s", "K8s", "K7s", "K6s", "K3s", "K2s", "QQ", "QJs", "QTs", "Q9s", "Q8s", "Q7s", "Q6s", "Q5s", "Q4s", "JJ", "JTs", "JTo", "J9s", "J8s", "J7s", "J6s", "TT", "T9s", "T8s", "T7s", "T6s", "99", "98s", "97s", "96s", "95s", "88", "87s", "86s", "85s", "77", "76s", "75s", "74s", "66", "65s", "64s", "63s", "55", "54s", "53s", "52s", "44", "43s", "42s", "33", "32s", "22" )),
                rcRange = Range(setOf("AKo", "K5s", "K4s" )),
                fcRange = Range(setOf("KTo", "QJo", "QTo", "T9o" )),
                valueRange = Range(setOf("AA", "AKs", "KK")),
                bluffRange = Range(setOf())
            ),
            "LJ" to RFIThreeBetRange(
                callRange = Range(setOf("AQo", "AJs", "AJo", "ATs", "ATo", "A9s", "A9o", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s", "KQs", "KQo", "KJs", "KJo", "KTs", "KTo", "K9s", "K8s", "K7s", "K6s", "K5s", "K4s", "K3s", "K2s", "QJo", "QTs", "QTo", "Q9s", "Q8s", "Q7s", "Q6s", "Q5s", "Q4s", "Q3s", "Q2s", "JJ", "JTs", "JTo", "J9s", "J8s", "J7s", "J6s", "J5s", "TT", "T9s", "T9o", "T8s", "T7s", "T6s", "99", "98s", "97s", "96s", "95s", "88", "86s", "85s", "77", "75s", "74s", "66", "64s", "63s", "55", "54s", "53s", "52s", "44", "43s", "42s", "33", "32s", "22" )),
                rcRange = Range(setOf("AQs", "QQ", "QJs", "87s", "76s", "65s" )),
                fcRange = Range(setOf("T9o", "98o", "87o")),
                valueRange = Range(setOf("AA", "AKs", "AKo", "KK")),
                bluffRange = Range(setOf())
            ),
            "HJ" to RFIThreeBetRange(
                callRange = Range(setOf("AJs", "AJo", "ATs", "ATo", "A9s", "A9o", "A8s", "A8o", "A7s", "A7o", "A6s", "A5s", "A4s", "A3s", "A2s", "KQs", "KQo", "KJs", "KJo", "KTs", "KTo", "K9s", "K9o", "K8s", "K7s", "K6s", "K5s", "K4s", "K3s", "K2s", "QJo", "QTo", "Q9s", "Q8s", "Q7s", "Q6s", "Q5s", "Q4s", "Q3s", "Q2s", "JJ", "JTs", "JTo", "J9s", "J8s", "J7s", "J6s", "J5s", "J4s", "J3s", "TT", "T9s", "T9o", "T8s", "T7s", "T6s", "99", "98s", "98o", "97s", "96s", "95s", "88", "86s", "85s", "77", "75s", "74s", "66", "64s", "63s", "55", "53s", "52s", "44", "43s", "42s", "33", "32s", "22" )),
                rcRange = Range(setOf("A4o", "QJs", "QTs", "87s", "76s", "65s" )),
                fcRange = Range(setOf("K9o", "87o", "76o", "65o")),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "KK", "QQ")),
                bluffRange = Range(setOf())
            ),
            "CO" to RFIThreeBetRange(
                callRange = Range(setOf("AQo", "AJs", "AJo", "ATs", "ATo", "A9s", "A9o", "A8s", "A7s", "A6s", "A3s", "A2s", "KQs", "KJo", "KTs", "KTo", "K9s", "K8s", "K7s", "K6s", "K5s", "K4s", "QJs", "QJo", "QTs", "QTo", "Q9s", "Q8s", "Q7s", "Q6s", "Q5s", "Q4s", "JTs", "JTo", "J9s", "J8s", "J7s", "J6s", "TT", "T9s", "T8s", "T7s", "99", "98s", "97s", "96s", "88", "87s", "86s", "85s", "77", "76s", "75s", "74s", "66", "65s", "64s", "63s", "55", "54s", "53s", "44", "43s", "33", "22" )),
                rcRange = Range(setOf("A5s","A5o", "A4s","KQo", "KJs", "K3s", "K2s", "Q3s", "Q2s" )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "KK", "QQ", "JJ")),
                bluffRange = Range(setOf())
            ),
            "BTN" to RFIThreeBetRange(
                callRange = Range(setOf("AJo", "ATs", "ATo", "A9s", "A9o", "A8s", "A8o", "A7s", "A6s", "A4s", "A3s", "A2s", "KQo", "KJs", "K9s", "K8s", "K7s", "K6s", "K5s", "K4s", "K3s", "K2s", "QJs", "QTs", "Q9s", "Q8s", "Q7s", "Q6s", "Q5s", "Q4s", "JTo", "J9s", "J8s", "J7s", "J6s", "J5s", "J4s", "T9o", "T8s", "T7s", "T6s", "98s", "97s", "96s", "88", "87s", "86s", "85s", "77", "76s", "75s", "74s", "66", "65s", "64s", "55", "54s", "53s", "44", "43s", "33", "22" )),
                rcRange = Range(setOf("A7o","A5o", "KJo", "KTs", "KTo", "K9o", "QJo", "QTo", "Q3s", "Q2s", "JTs", "99" )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AQo", "AJs", "A5s", "KK", "KQs", "QQ", "JJ", "TT", "T9s")),
                bluffRange = Range(setOf())
            ),
            "SB" to RFIThreeBetRange(
                callRange = Range(setOf("AJo", "ATs", "ATo", "A9s", "A9o", "A8s", "A8o", "A7s", "A7o", "A6s", "A5o", "A3s", "A2s", "KQo", "KJo", "KTo", "K9o", "K8s", "K8o", "K7s", "K6s", "K5s", "K4s", "K3s", "K2s", "QJo", "QTs", "QTo", "Q9s", "Q9o", "Q8s", "Q7s", "Q6s", "Q5s", "Q4s", "Q3s", "Q2s", "JTo", "J9s", "J9o", "J8s", "J7s", "J6s", "J5s", "J4s", "J3s", "T9o", "T8s", "T7s", "T6s", "98o", "97s", "96s", "95s", "88", "86s", "85s", "77", "75s", "74s", "66", "64s", "63s", "55", "53s", "52s", "44", "43s", "42s", "33", "32s", "22" )),
                rcRange = Range(setOf("A6o", "A3o", "KTs", "K9s", "JTs", "J2s", "T9s", "99", "87s", "76s", "54s" )),
                fcRange = Range(setOf( )),
                valueRange = Range(setOf("AA", "AKs", "AKo", "AQs", "AQo", "AJs", "A5s", "A4s", "A3o", "A2o", "KK", "KQs", "KJs", "QQ", "QJs", "JJ", "TT", "T3s", "T2s", "98s")),
                bluffRange = Range(setOf("Q8o", "J8o"))
            )
        )
    )
}
