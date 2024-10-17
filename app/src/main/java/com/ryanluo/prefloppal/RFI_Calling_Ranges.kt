package com.ryanluo.prefloppal

object RFICallingRanges {
    val RANGES = mapOf(
        "MP" to mapOf(
            "UTG" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66",
                "AQs", "AJs", "ATs", "KQs",
                "JTs", "T9s", "98s",
                "AQo"
            ))
        ),
        "CO" to mapOf(
            "UTG" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66",
                "AJs", "ATs", "KQs", "KJs", "KTs","QJs", "QTs", "JTs", "T9s", "98s"
            )),
            "MP" to Range(setOf(
                "JJ", "TT", "99", "88", "77",
                "AQs", "AJs", "ATs", "A5s", "KQs", "KJs", "QJs",
                "AQo", "AJo"
            ))
        ),
        "BTN" to mapOf(
            "UTG" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66",
                "AQs", "AJs", "ATs", "A9s", "A5s", "A4s", "KQs", "KJs", "KTs", "QJs", "QTs", "JTs",
                "AQo", "AJo", "KQo"
            )),
            "MP" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66", "55",
                "AQs", "AJs", "ATs", "A9s", "A5s", "A4s", "KQs", "KJs", "KTs", "QJs", "QTs", "JTs", "T9s",
                "AQo", "AJo", "ATo", "KQo", "KJo"
            )),
            "CO" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66", "55",
                "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "KQs", "KJs", "KTs", "K9s", "QJs", "QTs", "Q9s", "JTs", "J9s", "T9s", "98s",
                "AJo", "ATo", "A9o", "KQo", "KJo", "QJo"
            ))
        ),
        "SB" to mapOf(
            "UTG" to Range(setOf(
                "JJ", "TT", "99", "88", "77",
                "AQs", "AJs", "ATs", "A5s", "KQs", "KJs",
                "AQo"
            )),
            "MP" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66",
                "AQs", "AJs", "ATs", "A9s", "A5s", "KQs", "KJs", "QJs",
                "AQo", "AJo"
            )),
            "CO" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66", "55",
                "AQs", "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "KQs", "KJs", "KTs", "QJs", "QTs", "JTs",
                "AQo", "AJo", "ATo", "KQo", "KJo", "QJo"
            )),
            "BTN" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66", "55", "44",
                "AQs", "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s", "KQs", "KJs", "KTs", "K9s", "QJs", "QTs", "Q9s", "JTs", "J9s", "T9s", "98s", "87s",
                "AQo", "AJo", "ATo", "A9o", "A8o", "KQo", "KJo", "KTo", "QJo", "QTo", "JTo"
            ))
        ),
        "BB" to mapOf(
            "UTG" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66", "55", "44", "33", "22",
                "AQs", "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s",
                "KQs", "KJs", "KTs", "K9s", "K8s", "K7s", "K6s", "K5s", "K4s", "K3s", "K2s",
                "QJs", "QTs", "Q9s", "Q8s", "Q7s", "Q6s",
                "JTs", "J9s", "J8s", "J7s",
                "T9s", "T8s", "T7s",
                "98s", "97s", "87s", "76s", "65s", "54s",
                "AQo", "AJo", "ATo", "A9o", "A8o",
                "KQo", "KJo", "KTo",
                "QJo", "QTo",
                "JTo"
            )),
            "MP" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66", "55", "44", "33", "22",
                "AQs", "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s",
                "KQs", "KJs", "KTs", "K9s", "K8s", "K7s", "K6s", "K5s", "K4s", "K3s", "K2s",
                "QJs", "QTs", "Q9s", "Q8s", "Q7s", "Q6s", "Q5s",
                "JTs", "J9s", "J8s", "J7s", "J6s",
                "T9s", "T8s", "T7s", "T6s",
                "98s", "97s", "96s", "87s", "86s", "76s", "75s", "65s", "64s", "54s", "53s",
                "AQo", "AJo", "ATo", "A9o", "A8o", "A7o",
                "KQo", "KJo", "KTo", "K9o",
                "QJo", "QTo", "Q9o",
                "JTo", "J9o",
                "T9o"
            )),
            "CO" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66", "55", "44", "33", "22",
                "AQs", "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s",
                "KQs", "KJs", "KTs", "K9s", "K8s", "K7s", "K6s", "K5s", "K4s", "K3s", "K2s",
                "QJs", "QTs", "Q9s", "Q8s", "Q7s", "Q6s", "Q5s", "Q4s",
                "JTs", "J9s", "J8s", "J7s", "J6s", "J5s",
                "T9s", "T8s", "T7s", "T6s", "T5s",
                "98s", "97s", "96s", "95s", "87s", "86s", "85s", "76s", "75s", "74s", "65s", "64s", "63s", "54s", "53s", "52s", "43s",
                "AQo", "AJo", "ATo", "A9o", "A8o", "A7o", "A6o", "A5o",
                "KQo", "KJo", "KTo", "K9o", "K8o",
                "QJo", "QTo", "Q9o", "Q8o",
                "JTo", "J9o", "J8o",
                "T9o", "T8o",
                "98o", "87o"
            )),
            "BTN" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66", "55", "44", "33", "22",
                "AQs", "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s",
                "KQs", "KJs", "KTs", "K9s", "K8s", "K7s", "K6s", "K5s", "K4s", "K3s", "K2s",
                "QJs", "QTs", "Q9s", "Q8s", "Q7s", "Q6s", "Q5s", "Q4s", "Q3s",
                "JTs", "J9s", "J8s", "J7s", "J6s", "J5s", "J4s",
                "T9s", "T8s", "T7s", "T6s", "T5s", "T4s",
                "98s", "97s", "96s", "95s", "94s", "87s", "86s", "85s", "84s", "76s", "75s", "74s", "65s", "64s", "63s", "54s", "53s", "52s", "43s", "42s",
                "AQo", "AJo", "ATo", "A9o", "A8o", "A7o", "A6o", "A5o", "A4o", "A3o",
                "KQo", "KJo", "KTo", "K9o", "K8o", "K7o",
                "QJo", "QTo", "Q9o", "Q8o", "Q7o",
                "JTo", "J9o", "J8o", "J7o",
                "T9o", "T8o", "T7o",
                "98o", "97o", "87o", "76o"
            )),
            "SB" to Range(setOf(
                "JJ", "TT", "99", "88", "77", "66", "55", "44", "33", "22",
                "AQs", "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s",
                "KQs", "KJs", "KTs", "K9s", "K8s", "K7s", "K6s", "K5s", "K4s", "K3s", "K2s",
                "QJs", "QTs", "Q9s", "Q8s", "Q7s", "Q6s", "Q5s", "Q4s", "Q3s", "Q2s",
                "JTs", "J9s", "J8s", "J7s", "J6s", "J5s", "J4s", "J3s", "J2s",
                "T9s", "T8s", "T7s", "T6s", "T5s", "T4s", "T3s", "T2s",
                "98s", "97s", "96s", "95s", "94s", "93s", "92s", "87s", "86s", "85s", "84s", "83s", "82s", "76s", "75s", "74s", "73s", "72s", "65s", "64s", "63s", "62s", "54s", "53s", "52s", "43s", "42s", "32s",
                "AQo", "AJo", "ATo", "A9o", "A8o", "A7o", "A6o", "A5o", "A4o", "A3o", "A2o",
                "KQo", "KJo", "KTo", "K9o", "K8o", "K7o", "K6o", "K5o",
                "QJo", "QTo", "Q9o", "Q8o", "Q7o", "Q6o",
                "JTo", "J9o", "J8o", "J7o", "J6o",
                "T9o", "T8o", "T7o", "T6o",
                "98o", "97o", "96o", "87o", "86o", "76o", "65o"
            ))
        )
    )
}