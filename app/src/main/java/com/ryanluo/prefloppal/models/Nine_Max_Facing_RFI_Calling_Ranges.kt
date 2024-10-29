package com.ryanluo.prefloppal.models

import com.ryanluo.prefloppal.utils.Range


object Nine_Max_RFICallingRanges {
    val RANGES = mapOf(
        "UTG+1" to mapOf(
            "UTG" to Range(setOf(
                "JJ", "TT", "99", "88",
                "AQs", "AJs",
                "KQs"
            ))
        ),
        "UTG+2" to mapOf(
            "UTG" to Range(setOf(
                "JJ", "TT", "99", "88",
                "AQs", "AJs",
                "KQs",
                "QJs",
                "JTs"
            )),
            "UTG+1" to Range(setOf(
                "JJ", "TT", "99", "88",
                "AQs", "AJs",
                "KQs",
                "QJs",
                "JTs"
            ))
        ),
        "LJ" to mapOf(
            "UTG" to Range(setOf(
                "JJ", "TT", "99", "88", "77",
                "AQs", "AJs",
                "KQs",
                "QJs",
                "JTs",
                "T9s"
            )),
            "UTG+1" to Range(setOf(
                "JJ", "TT", "99", "88", "77",
                "AQs", "AJs",
                "KQs",
                "QJs",
                "JTs",
                "T9s"
            )),
            "UTG+2" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66",
                "AQs", "AJs",
                "KQs",
                "QJs",
                "JTs",
                "T9s"
            ))
        ),
        "HJ" to mapOf(
            "UTG" to Range(setOf(
                "JJ", "TT", "99", "88", "77",
                "AQs", "AJs",
                "KQs",
                "QJs",
                "JTs",
                "T9s"
            )),
            "UTG+1" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66",
                "AQs", "AJs",
                "KQs",
                "QJs",
                "JTs",
                "T9s",
                "98s"
            )),
            "UTG+2" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66", "55",
                "AQs", "AJs",
                "KQs",
                "QJs",
                "JTs",
                "T9s",
                "98s"
            )),
            "LJ" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66", "55",
                "ATs",
                "KJs", "KTs",
                "QJs", "QTs",
                "JTs",
                "T9s",
                "98s",
                "87s"
            ))
        ),
        "CO" to mapOf(
            "UTG" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66",
                "AJs", "ATs",
                "KQs", "KJs", "KTs",
                "QJs", "QTs",
                "JTs",
                "T9s",
                "98s"
            )),
            "UTG+1" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66",
                "AJs", "ATs",
                "KQs", "KJs", "KTs",
                "QJs", "QTs",
                "JTs",
                "T9s",
                "98s"
            )),
            "UTG+2" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66", "55",
                "AJs", "ATs",
                "KQs", "KJs", "KTs",
                "QJs", "QTs",
                "JTs",
                "T9s",
                "98s"
            )),
            "LJ" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66", "55", "44",
                "ATs",
                "KJs", "KTs",
                "QJs", "QTs",
                "JTs",
                "T9s",
                "98s"

            )),
            "HJ" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66", "55", "44",
                "ATs",
                "KJs", "KTs",
                "QJs", "QTs",
                "JTs",
                "T9s",
                "98s",
                "87s"
            ))
        ),
        "BTN" to mapOf(
            "UTG" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66", "55", "44", "33", "22",
                "AQs", "AJs", "ATs",
                "KQs", "KJs", "KTs",
                "QJs", "QTs",
                "JTs",
                "T9s",
                "98s",
                "87s"

            )),
            "UTG+1" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66", "55", "44", "33", "22",
                "AQs", "AJs", "ATs",
                "KQs", "KJs", "KTs",
                "QJs", "QTs",
                "JTs", "J9s",
                "T9s",
                "98s",
                "87s",
                "76s"

            )),
            "UTG+2" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66", "55", "44", "33", "22",
                "AJs", "ATs",
                "KQs", "KJs", "KTs",
                "KQo","QJs", "QTs",
                "AJo", "JTs", "J9s",
                "T9s",
                "98s",
                "87s",
                "76s"

            )),
            "LJ" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66", "55", "44", "33", "22",
                "AJs", "ATs", "A9s",
                "KQs", "KJs", "KTs",
                "KQo", "QJs", "QTs",
                "AJo", "JTs", "J9s",
                "T9s",
                "98s",
                "87s",
                "76s"

            )),
            "HJ" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66", "55", "44", "33", "22",
                "ATs", "A9s",
                "KQs", "KJs", "KTs", "K9s",
                "KQo", "QJs", "QTs", "Q9s",
                "AJo", "JTs", "J9s",
                "T9s", "T8s",
                "98s", "97s",
                "87s",
                "76s"

            )),
            "CO" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66", "55", "44", "33", "22",
                "ATs", "A9s", "A5s", "A4s",
                "KQs", "KJs", "KTs", "K9s",
                "KQo", "QJs", "QTs", "Q9s",
                "AJo", "KJo", "JTs", "J9s",
                "ATo", "T9s", "T8s",
                "98s", "97s",
                "87s",
                "76s"

            ))
        ),
        "SB" to mapOf(
            "UTG" to Range(setOf(
                "JJ", "TT", "99", "88", "77",
                "AQs", "AJs", "ATs",
                "AKo", "KQs", "KJs",
                "AQo", "QJs",
                "JTs",
                "T9s"

            )),
            "UTG+1" to Range(setOf(
                "JJ", "TT", "99", "88", "77",
                "AQs", "AJs", "ATs",
                "AKo", "KQs", "KJs",
                "AQo", "QJs",
                "JTs",
                "T9s"

            )),
            "UTG+2" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66",
                "AQs", "AJs", "ATs",
                "AKo", "KQs", "KJs", "KTs",
                "AQo", "QJs", "QTs",
                "JTs",
                "T9s",
                "98s"

            )),
            "LJ" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66", "55",
                "AJs", "ATs",
                "KQs", "KJs", "KTs",
                "AQo", "QJs", "QTs",
                "JTs",
                "T9s",
                "98s"

            )),
            "HJ" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66", "55", "44",
                "AJs", "ATs",
                "KQs", "KJs", "KTs",
                "AQo", "QJs", "QTs",
                "JTs",
                "T9s",
                "98s",
                "87s"

            )),
            "CO" to Range(setOf(

            )),
            "BTN" to Range(setOf(

            ))
        ),
        "BB" to mapOf(
            "UTG" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66", "55", "44", "33", "22",
                "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s",
                "AKo", "KQs", "KJs", "KTs", "K9s", "K8s", "K7s", "K6s", "K5s",
                "AQo", "KQo", "QJs", "QTs", "Q9s", "Q8s", "Q7s",
                "AJo", "KJo", "QJo", "JTs", "J9s", "J8s", "J7s",
                "ATo", "KTo", "QTo", "JTo", "T9s", "T8s", "T7s",
                "98s", "97s", "96s",
                "87s"

            )),
            "UTG+1" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66", "55", "44", "33", "22",
                "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s",
                "AKo", "KQs", "KJs", "KTs", "K9s", "K8s", "K7s", "K6s", "K5s",
                "AQo", "KQo", "QJs", "QTs", "Q9s", "Q8s", "Q7s",
                "AJo", "KJo", "QJo", "JTs", "J9s", "J8s", "J7s",
                "ATo", "KTo", "QTo", "JTo", "T9s", "T8s", "T7s",
                "98s", "97s", "96s",
                "87s"

            )),
            "UTG+2" to Range(setOf(
                "TT", "99", "88", "77", "66", "55", "44", "33", "22",
                "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s",
                "KQs", "KJs", "KTs", "K9s", "K8s", "K7s", "K6s", "K5s", "K4s",
                "AQo", "KQo", "QJs", "QTs", "Q9s", "Q8s", "Q7s", "Q6s",
                "AJo", "KJo", "QJo", "JTs", "J9s", "J8s", "J7s", "J6s",
                "ATo", "KTo", "QTo", "JTo", "T9s", "T8s", "T7s", "T6s",
                "98s", "97s", "96s",
                "87s"

            )),
            "LJ" to Range(setOf(
                "99", "88", "77", "66", "55", "44", "33", "22",
                "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s",
                "KJs", "KTs", "K9s", "K8s", "K7s", "K6s", "K5s", "K4s", "K3s", "K2s",
                "AQo", "KQo", "QJs", "QTs", "Q9s", "Q8s", "Q7s", "Q6s", "Q5s", "Q4s",
                "AJo", "KJo", "QJo", "JTs", "J9s", "J8s", "J7s", "J6s", "J5s",
                "ATo", "KTo", "QTo", "JTo", "T9s", "T8s", "T7s", "T6s", "T5s",
                "98s", "97s", "96s", "95s",
                "87s", "86s",
                "76s"

            )),
            "HJ" to Range(setOf(
                "99", "88", "77", "66", "55", "44", "33", "22",
                "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s",
                "KJs", "KTs", "K9s", "K8s", "K7s", "K6s", "K5s", "K4s", "K3s", "K2s",
                "KQo", "QJs", "QTs", "Q9s", "Q8s", "Q7s", "Q6s", "Q5s", "Q4s",
                "AJo", "KJo", "QJo", "JTs", "J9s", "J8s", "J7s", "J6s", "J5s",
                "ATo", "KTo", "QTo", "JTo", "T9s", "T8s", "T7s", "T6s", "T5s",
                "98s", "97s", "96s", "95s",
                "87s", "86s"

            )),
            "CO" to Range(setOf(
                "99", "88", "77", "66", "55", "44", "33", "22",
                "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s",
                "KTs", "K9s", "K8s", "K7s", "K6s", "K5s", "K4s", "K3s", "K2s",
                "QTs", "Q9s", "Q8s", "Q7s", "Q6s", "Q5s", "Q4s",
                "KJo", "QJo", "JTs", "J9s", "J8s", "J7s", "J6s", "J5s", "J4s",
                "ATo", "KTo", "QTo", "JTo", "T9s", "T8s", "T7s", "T6s", "T5s", "T4s",
                "A9o", "K9o", "Q9o", "J9o", "T9o", "98s", "97s", "96s", "95s", "94s",
                "A8o", "98o", "87s", "86s",
                "A5o"

            )),
            "BTN" to Range(setOf(
                "88", "77", "66", "55", "44", "33", "22",
                "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s",
                "K9s", "K8s", "K7s", "K6s", "K5s", "K4s", "K3s", "K2s",
                "Q9s", "Q8s", "Q7s", "Q6s", "Q5s", "Q4s", "Q3s", "Q2s",
                "KJo", "QJo", "J9s", "J8s", "J7s", "J6s", "J5s", "J4s", "J3s", "J2s",
                "ATo", "KTo", "QTo", "JTo", "T9s", "T8s", "T7s", "T6s", "T5s", "T4s", "T3s", "T2s",
                "A9o", "K9o", "Q9o", "J9o", "T9o", "98s", "97s", "96s", "95s", "94s", "93s", "92s",
                "A8o", "K8o", "Q8o", "J8o", "T8o", "98o", "87s", "86s", "85s", "84s", "83s", "82s",
                "A7o", "K7o", "Q7o", "J7o", "T7o", "97o", "87o", "72s",
                "A6o", "K6o", "J6o", "T6o", "96o", "86o", "76o", "62s",
                "A5o",
                "A4o"

            )),
            "SB" to Range(setOf(
                "88", "77", "66", "55", "44", "33", "22",
                "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s",
                "K9s", "K8s", "K7s", "K6s", "K5s", "K4s", "K3s", "K2s",
                "Q9s", "Q8s", "Q7s", "Q6s", "Q5s", "Q4s", "Q3s", "Q2s",
                "KJo", "QJo", "J8s", "J7s", "J6s", "J5s", "J4s", "J3s", "J2s",
                "ATo", "KTo", "QTo", "JTo", "T7s", "T6s", "T5s", "T4s", "T3s", "T2s",
                "A9o", "K9o", "Q9o", "J9o", "T9o", "97s", "96s", "95s", "94s", "93s", "92s",
                "A8o", "K8o", "Q8o", "J8o", "T8o", "98o", "86s", "85s", "84s", "83s", "82s",
                "A7o", "K7o", "Q7o", "J7o", "T7o", "97o", "87o", "75s", "74s", "73s", "72s",
                "A6o", "K6o", "Q6o", "J6o", "T6o", "96o", "86o", "64s", "63s", "62s",
                "A5o", "K5o", "Q5o", "53s", "52s",
                "A4o", "K4o", "43s", "42s",
                "A3o", "32s"

            ))
        )
    )
}
