package com.ryanluo.prefloppal

object Facing_RFI_Ranges{

    val POSITIONS = listOf("UTG", "MP", "CO", "BTN", "SB", "BB")

    val FACING_RFI_RANGES = mapOf(
        "MP" to mapOf(
            "UTG" to Range(setOf(
                "AA", "KK", "QQ", "JJ", "TT", "99", "88",
                "AKs", "AQs", "AJs", "ATs", "KQs",
                "AKo", "AQo"
            ))
        ),
        "CO" to mapOf(
            "UTG" to Range(setOf(
                "AA", "KK", "QQ", "JJ", "TT", "99", "88", "77",
                "AKs", "AQs", "AJs", "ATs", "A5s", "KQs", "KJs", "QJs",
                "AKo", "AQo"
            )),
            "MP" to Range(setOf(
                "AA", "KK", "QQ", "JJ", "TT", "99", "88", "77",
                "AKs", "AQs", "AJs", "ATs", "A5s", "KQs", "KJs", "QJs",
                "AKo", "AQo", "AJo"
            ))
        ),
        "BTN" to mapOf(
            "UTG" to Range(setOf(
                "AA", "KK", "QQ", "JJ", "TT", "99", "88", "77", "66",
                "AKs", "AQs", "AJs", "ATs", "A9s", "A5s", "A4s", "KQs", "KJs", "KTs", "QJs", "QTs", "JTs",
                "AKo", "AQo", "AJo", "KQo"
            )),
            "MP" to Range(setOf(
                "AA", "KK", "QQ", "JJ", "TT", "99", "88", "77", "66", "55",
                "AKs", "AQs", "AJs", "ATs", "A9s", "A5s", "A4s", "KQs", "KJs", "KTs", "QJs", "QTs", "JTs", "T9s",
                "AKo", "AQo", "AJo", "ATo", "KQo", "KJo"
            )),
            "CO" to Range(setOf(
                "AA", "KK", "QQ", "JJ", "TT", "99", "88", "77", "66", "55",
                "AKs", "AQs", "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "KQs", "KJs", "KTs", "K9s", "QJs", "QTs", "Q9s", "JTs", "J9s", "T9s", "98s",
                "AKo", "AQo", "AJo", "ATo", "A9o", "KQo", "KJo", "QJo"
            ))
        ),
        "SB" to mapOf(
            "UTG" to Range(setOf(
                "AA", "KK", "QQ", "JJ", "TT", "99", "88", "77",
                "AKs", "AQs", "AJs", "ATs", "A5s", "KQs", "KJs",
                "AKo", "AQo"
            )),
            "MP" to Range(setOf(
                "AA", "KK", "QQ", "JJ", "TT", "99", "88", "77", "66",
                "AKs", "AQs", "AJs", "ATs", "A9s", "A5s", "KQs", "KJs", "QJs",
                "AKo", "AQo", "AJo"
            )),
            "CO" to Range(setOf(
                "AA", "KK", "QQ", "JJ", "TT", "99", "88", "77", "66", "55",
                "AKs", "AQs", "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "KQs", "KJs", "KTs", "QJs", "QTs", "JTs",
                "AKo", "AQo", "AJo", "ATo", "KQo", "KJo", "QJo"
            )),
            "BTN" to Range(setOf(
                "AA", "KK", "QQ", "JJ", "TT", "99", "88", "77", "66", "55", "44",
                "AKs", "AQs", "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s", "KQs", "KJs", "KTs", "K9s", "QJs", "QTs", "Q9s", "JTs", "J9s", "T9s", "98s", "87s",
                "AKo", "AQo", "AJo", "ATo", "A9o", "A8o", "KQo", "KJo", "KTo", "QJo", "QTo", "JTo"
            ))
        ),
        "BB" to mapOf(
            "UTG" to Range(setOf(
                "AA", "KK", "QQ", "JJ", "TT", "99", "88", "77", "66", "55", "44", "33", "22",
                "AKs", "AQs", "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s", "KQs", "KJs", "KTs", "K9s", "K8s", "K7s", "K6s", "QJs", "QTs", "Q9s", "JTs", "J9s", "T9s", "98s", "87s", "76s", "65s",
                "AKo", "AQo", "AJo", "ATo", "A9o", "A8o", "KQo", "KJo", "QJo"
            )),
            "MP" to Range(setOf(
                "AA", "KK", "QQ", "JJ", "TT", "99", "88", "77", "66", "55", "44", "33", "22",
                "AKs", "AQs", "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s", "KQs", "KJs", "KTs", "K9s", "K8s", "K7s", "K6s", "QJs", "QTs", "Q9s", "JTs", "J9s", "T9s", "98s", "87s", "76s", "65s", "54s",
                "AKo", "AQo", "AJo", "ATo", "A9o", "A8o", "A7o", "KQo", "KJo", "KTo", "QJo", "QTo", "JTo"
            )),
            "CO" to Range(setOf(
                "AA", "KK", "QQ", "JJ", "TT", "99", "88", "77", "66", "55", "44", "33", "22",
                "AKs", "AQs", "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s", "KQs", "KJs", "KTs", "K9s", "K8s", "K7s", "K6s", "K5s", "QJs", "QTs", "Q9s", "Q8s", "JTs", "J9s", "J8s", "T9s", "T8s", "98s", "97s", "87s", "86s", "76s", "75s", "65s", "64s", "54s", "53s",
                "AKo", "AQo", "AJo", "ATo", "A9o", "A8o", "A7o", "A6o", "A5o", "KQo", "KJo", "KTo", "K9o", "QJo", "QTo", "Q9o", "JTo", "J9o", "T9o"
            )),
            "BTN" to Range(setOf(
                "AA", "KK", "QQ", "JJ", "TT", "99", "88", "77", "66", "55", "44", "33", "22",
                "AKs", "AQs", "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s", "KQs", "KJs", "KTs", "K9s", "K8s", "K7s", "K6s", "K5s", "K4s", "K3s", "K2s", "QJs", "QTs", "Q9s", "Q8s", "Q7s", "Q6s", "Q5s", "JTs", "J9s", "J8s", "J7s", "T9s", "T8s", "T7s", "98s", "97s", "96s", "87s", "86s", "85s", "76s", "75s", "65s", "64s", "54s", "53s", "43s",
                "AKo", "AQo", "AJo", "ATo", "A9o", "A8o", "A7o", "A6o", "A5o", "A4o", "A3o", "A2o", "KQo", "KJo", "KTo", "K9o", "K8o", "K7o", "QJo", "QTo", "Q9o", "Q8o", "JTo", "J9o", "J8o", "T9o", "98o", "87o"
            )),
            "SB" to Range(setOf(
                "AA", "KK", "QQ", "JJ", "TT", "99", "88", "77", "66", "55", "44", "33", "22",
                "AKs", "AQs", "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s", "KQs", "KJs", "KTs", "K9s", "K8s", "K7s", "K6s", "K5s", "K4s", "K3s", "K2s", "QJs", "QTs", "Q9s", "Q8s", "Q7s", "Q6s", "Q5s", "Q4s", "JTs", "J9s", "J8s", "J7s", "J6s", "T9s", "T8s", "T7s", "98s", "97s", "96s", "87s", "86s", "85s", "76s", "75s", "74s", "65s", "64s", "63s", "54s", "53s", "52s", "43s", "42s", "32s",
                "AKo", "AQo", "AJo", "ATo", "A9o", "A8o", "A7o", "A6o", "A5o", "A4o", "A3o", "A2o", "KQo", "KJo", "KTo", "K9o", "K8o", "K7o", "K6o", "K5o", "QJo", "QTo", "Q9o", "Q8o", "Q7o", "JTo", "J9o", "J8o", "T9o", "98o", "87o", "76o"
            ))
        )
    )
}