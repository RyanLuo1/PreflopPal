package com.ryanluo.prefloppal

object Nine_Max_RFI_Ranges{

    val POSITIONS = listOf("UTG", "UTG+1", "UTG+2", "LJ", "HJ", "CO", "BTN", "SB", "BB")

    val RFI_RANGES = mapOf(
        "UTG" to Range(setOf(
            "AA", "KK", "QQ", "JJ", "TT", "99", "88", "77", "66",
            "AKs", "AQs", "AJs", "ATs", "A9s", "A5s",
            "AKo", "KQs", "KJs", "KTs",
            "AQo", "QJs", "QTs",
            "JTs",
            "T9s",
            "98s"
        )),
        "UTG+1" to Range(setOf(
            "AA", "KK", "QQ", "JJ", "TT", "99", "88", "77", "66",
            "AKs", "AQs", "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s",
            "AKo", "KQs", "KJs", "KTs", "K9s",
            "AQo", "KQo", "QJs", "QTs", "Q9s",
            "AJo", "JTs", "J9s",
            "T9s",
            "98s",
            "87s"
        )),
        "UTG+2" to Range(setOf(
            "AA", "KK", "QQ", "JJ", "TT", "99", "88", "77", "66", "55",
            "AKs", "AQs", "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s",
            "AKo", "KQs", "KJs", "KTs", "K9s",
            "AQo", "KQo", "QJs", "QTs", "Q9s",
            "AJo", "JTs", "J9s",
            "T9s",
            "98s",
            "87s",
            "76s"
        )),
        "LJ" to Range(setOf(
            "AA", "KK", "QQ", "JJ", "TT", "99", "88", "77", "66", "55", "44",
            "AKs", "AQs", "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s",
            "AKo", "KQs", "KJs", "KTs", "K9s",
            "AQo", "KQo", "QJs", "QTs", "Q9s",
            "AJo", "KJo", "JTs", "J9s",
            "ATo", "T9s",
            "98s",
            "87s",
            "76s",
            "65s"
        )),
        "HJ" to Range(setOf(
            "AA", "KK", "QQ", "JJ", "TT", "99", "88", "77", "66", "55", "44", "33", "22",
            "AKs", "AQs", "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s",
            "AKo", "KQs", "KJs", "KTs", "K9s", "K8s",
            "AQo", "KQo", "QJs", "QTs", "Q9s",
            "AJo", "KJo", "QJo", "JTs", "J9s",
            "ATo", "T9s", "T8s",
            "98s", "97s",
            "87s",
            "76s",
            "65s",
            "54s",
        )),
        "CO" to Range(setOf(
            "AA", "KK", "QQ", "JJ", "TT", "99", "88", "77", "66", "55", "44", "33", "22",
            "AKs", "AQs", "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s",
            "AKo", "KQs", "KJs", "KTs", "K9s", "K8s", "K7s",
            "AQo", "KQo", "QJs", "QTs", "Q9s", "Q8s",
            "AJo", "KJo", "QJo", "JTs", "J9s", "J8s",
            "ATo", "KTo", "QTo", "JTo", "T9s", "T8s",
            "A9o", "98s", "97s",
            "87s", "86s",
            "76s", "75s",
            "65s", "64s",
            "54s",
            "43s"
        )),
        "BTN" to Range(setOf(
            "AA", "KK", "QQ", "JJ", "TT", "99", "88", "77", "66", "55", "44", "33", "22",
            "AKs", "AQs", "AJs", "ATs", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s",
            "AKo", "KQs", "KJs", "KTs", "K9s", "K8s", "K7s", "K6s", "K5s", "K4s", "K3s", "K2s",
            "AQo", "KQo", "QJs", "QTs", "Q9s", "Q8s", "Q7s", "Q6s", "Q5s", "Q4s", "Q3s", "Q2s",
            "AJo", "KJo", "QJo", "JTs", "J9s", "J8s", "J7s", "J6s",
            "ATo", "KTo", "QTo", "JTo", "T9s", "T8s", "T7s", "T6s",
            "A9o", "K9o", "Q9o", "J9o", "T9o", "98s", "97s", "96s",
            "A8o", "K8o", "Q8o", "J8o", "T8o", "98o", "87s", "86s", "85s",
            "A7o", "K7o", "97o", "87o", "76s", "75s", "74s",
            "A6o", "76o", "65s", "64s",
            "A5o", "54s", "53s",
            "A4o", "43s",
            "A3o", "32s",
            "A2o"
        )),
        "SB" to Range(setOf(

        ))

        //BB does not RFI
    )
}