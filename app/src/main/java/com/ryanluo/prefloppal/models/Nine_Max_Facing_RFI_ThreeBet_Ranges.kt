package com.ryanluo.prefloppal.models

import com.ryanluo.prefloppal.utils.Range

object Nine_Max_RFIThreeBetRanges {

    val RANGES = mapOf(
        "UTG+1" to mapOf(
            "UTG" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ")),
                bluffRange = Range(setOf("ATs", "KJs", "AQo"))
            )
        ),
        "UTG+2" to mapOf(
            "UTG" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ")),
                bluffRange = Range(setOf("ATs", "KJs", "AQo"))
            ),
            "UTG+1" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ")),
                bluffRange = Range(setOf("ATs", "KJs", "AQo"))
            )
        ),
        "LJ" to mapOf(
            "UTG" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ")),
                bluffRange = Range(setOf("ATs", "A5s", "KJs", "AQo"))
            ),
            "UTG+1" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ")),
                bluffRange = Range(setOf("ATs", "A5s", "KJs", "AQo"))
            ),
            "UTG+2" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ")),
                bluffRange = Range(setOf("ATs", "A5s", "KJs", "AQo", "98s", "87s"))
            )
        ),
        "HJ" to mapOf(
            "UTG" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ")),
                bluffRange = Range(setOf("ATs", "A5s", "KJs", "AQo"))
            ),
            "UTG+1" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ")),
                bluffRange = Range(setOf("ATs", "A5s", "KJs", "AQo"))
            ),
            "UTG+2" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ")),
                bluffRange = Range(setOf("ATs", "A5s", "A4s", "A3s", "KJs", "AQo", "87s", "76s"))
            ),
            "LJ" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AQs", "AJs", "AKo", "KK", "KQs", "AQo", "QQ")),
                bluffRange = Range(setOf("A5s", "A4s", "A3s", "A2s", "KQo", "AJo", "ATo", "76s", "65s"))
            )
        ),
        "CO" to mapOf(
            "UTG" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AQs", "AKo", "KK", "QQ")),
                bluffRange = Range(setOf("A5s", "A4s", "AQo", "AJo"))
            ),
            "UTG+1" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AQs", "AKo", "KK", "QQ")),
                bluffRange = Range(setOf("A5s", "A4s", "AQo", "AJo"))
            ),
            "UTG+2" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AQs", "AKo", "KK", "QQ")),
                bluffRange = Range(setOf("A5s", "A4s", "A3s", "A2s", "AQo", "AJo", "87s", "76s"))
            ),
            "LJ" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AQs", "AJs", "AKo", "KK", "KQs", "AQo", "QQ")),
                bluffRange = Range(setOf("A5s", "A4s", "A3s", "A2s", "KQo", "AJo", "87s", "76s", "65s", "54s"))
            ),
            "HJ" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AQs", "AJs", "AKo", "KK", "KQs", "AQo", "QQ")),
                bluffRange = Range(setOf("A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s", "K9s", "KQo", "Q9s", "AJo", "J9s", "76s", "65s", "54s"))
            )
        ),
        "BTN" to mapOf(
            "UTG" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ")),
                bluffRange = Range(setOf("A5s", "A4s", "AQo", "KQo", "AJo", "76s"))
            ),
            "UTG+1" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AKo", "KK", "QQ")),
                bluffRange = Range(setOf("A5s", "A4s", "A3s", "A2s", "AQo", "KQo", "AJo"))
            ),
            "UTG+2" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AQs", "AKo", "KK", "AQo", "QQ")),
                bluffRange = Range(setOf("A9s", "A5s", "A4s", "A3s", "A2s", "KJo", "ATo", "65s", "54s"))
            ),
            "LJ" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AQs", "AKo", "KK", "AQo", "QQ")),
                bluffRange = Range(setOf("A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s", "KJo", "ATo", "65s", "54s"))
            ),
            "HJ" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AQs", "AJs", "AKo", "KK", "AQo", "QQ")),
                bluffRange = Range(setOf("A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s", "KJo", "ATo", "86s", "75s", "65s", "54s"))
            ),
            "CO" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AQs", "AJs", "AKo", "KK", "AQo", "QQ")),
                bluffRange = Range(setOf("A8s", "A7s", "A6s", "A3s", "A2s", "K8s", "Q8s", "QJo", "J8s", "KTo", "A9o", "86s", "75s", "65s", "64s", "54s", "43s"))
            )
        ),
        "SB" to mapOf(
            "UTG" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "KK", "QQ")),
                bluffRange = Range(setOf("A5s", "98s", "87s"))
            ),
            "UTG+1" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "KK", "QQ")),
                bluffRange = Range(setOf("A5s", "98s", "87s"))
            ),
            "UTG+2" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "KK", "QQ")),
                bluffRange = Range(setOf("A5s", "87s", "76s"))
            ),
            "LJ" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AQs", "AKo", "KK", "QQ")),
                bluffRange = Range(setOf("A5s", "A4s", "AJo", "87s", "76s"))
            ),
            "HJ" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AQs", "AKo", "KK", "QQ")),
                bluffRange = Range(setOf("A9s", "A5s", "A4s", "A3s", "AJo", "76s", "65s", "54s"))
            ),
            "CO" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AQs", "AJs", "ATs", "AKo", "KK", "KQs", "AQo", "QQ", "JJ", "TT")),
                bluffRange = Range(setOf("A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s", "KJs", "KTs", "K9s", "KQo", "QJs", "QTs", "Q9s", "AJo", "JTs", "J9s", "T9s", "99", "98s", "88", "87s", "77", "76s", "66", "65s", "55", "54s", "44"))
            ),
            "BTN" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AQs", "AJs", "ATs", "AKo", "KK", "KQs", "KJs", "AQo", "KQo", "QQ", "AJo", "JJ", "TT")),
                bluffRange = Range(setOf("A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s", "KTs", "K9s", "QJs", "QTs", "Q9s", "KJo", "QJo", "JTs", "J9s", "ATo", "T9s", "T8s", "99", "98s", "97s", "88", "87s", "86s", "77", "76s", "66", "65s", "55", "54s", "44", "33", "22"))
            )
        ),
        "BB" to mapOf(
            "UTG" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AQs", "KK", "QQ")),
                bluffRange = Range(setOf("86s", "76s", "75s", "65s", "64s", "54s", "43s"))
            ),
            "UTG+1" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AQs", "KK", "QQ")),
                bluffRange = Range(setOf("86s", "76s", "75s", "65s", "64s", "54s", "43s"))
            ),
            "UTG+2" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AQs", "AKo", "KK", "QQ", "JJ")),
                bluffRange = Range(setOf("86s", "85s", "76s", "75s", "74s", "65s", "64s", "54s", "53s", "43s"))
            ),
            "LJ" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AQs", "AJs", "AKo", "KK", "KQs", "QQ", "JJ", "TT")),
                bluffRange = Range(setOf("A9o", "85s", "75s", "74s", "65s", "64s", "54s", "53s", "43s"))
            ),
            "HJ" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AQs", "AJs", "AKo", "KK", "KQs", "AQo", "QQ", "JJ", "TT")),
                bluffRange = Range(setOf("A9o", "85s", "76s", "75s", "74s", "65s", "64s", "63s", "54s", "53s", "43s", "32s"))
            ),
            "CO" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AQs", "AJs", "ATs", "AKo", "KK", "KQs", "KJs", "AQo", "KQo", "QQ", "QJs", "AJo", "JJ", "TT")),
                bluffRange = Range(setOf("A4o", "A3o", "85s", "84s", "76s", "75s", "74s", "73s", "65s", "64s", "63s", "54s", "53s", "52s", "43s", "42s", "32s"))
            ),
            "BTN" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AQs", "AJs", "ATs", "AKo", "KK", "KQs", "KJs", "KTs", "AQo", "KQo", "QQ", "QJs", "QTs", "AJo", "JJ", "JTs", "TT", "99")),
                bluffRange = Range(setOf("76s", "75s", "74s", "73s", "Q6o", "65s", "64s", "63s", "K5o", "Q5o", "75o", "65o", "54s", "53s", "52s", "K4o", "43s", "42s", "A3o", "K3o", "32s", "A2o", "K2o"))
            ),
            "SB" to RFIThreeBetRange(
                valueRange = Range(setOf("AA", "AKs", "AQs", "AJs", "ATs", "AKo", "KK", "KQs", "KJs", "KTs", "AQo", "KQo", "QQ", "QJs", "QTs", "AJo", "JJ", "JTs", "TT", "99")),
                bluffRange = Range(setOf("J9s", "T9s", "T8s", "98s", "87s", "76s", "76o", "65s", "J5o", "T5o", "75o", "65o", "54s", "Q4o", "64o", "54o", "K3o", "Q3o", "A2o", "K2o", "Q2o"))
            )
        )
    )
}
