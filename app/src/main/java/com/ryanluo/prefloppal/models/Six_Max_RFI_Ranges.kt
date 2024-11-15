package com.ryanluo.prefloppal.models

import com.ryanluo.prefloppal.utils.Range

object Six_Max_RFI_Ranges{

    val POSITIONS = listOf("UTG", "MP", "CO", "BTN", "SB", "BB")

    val RANGES = mapOf(
        "UTG" to Range(setOf(
            "AA", "AKs", "AKo", "AQs", "AQo", "AJs", "AJo", "ATs", "ATo", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s",
            "KK", "KQs", "KQo", "KJs", "KTs", "K9s", "K8s",
            "QQ", "QJs", "QTs", "Q9s",
            "JJ", "JTs", "J9s",
            "TT", "T9s",
            "99", "88", "77", "66", "55"
        )),
        "MP" to Range(setOf(
            "AA", "AKs", "AKo", "AQs", "AQo", "AJs", "AJo", "ATs", "ATo", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s",
            "KK", "KQs", "KQo", "KJs", "KJo", "KTs", "K9s", "K8s", "K7s", "K6s",
            "QQ", "QJs", "QJo", "QTs", "Q9s",
            "JJ", "JTs", "J9s",
            "TT", "T9s", "T8s",
            "99", "98s",
            "88", "77", "66", "55", "44"
        )),
        "CO" to Range(setOf(
            "AA", "AKs", "AKo", "AQs", "AQo", "AJs", "AJo", "ATs", "ATo", "A9s", "A9o", "A8s", "A8o", "A7s", "A6s", "A5s", "A4s", "A3s", "A2s",
            "KK", "KQs", "KQo", "KJs", "KJo", "KTs", "KTo", "K9s", "K8s", "K7s", "K6s", "K5s", "K4s", "K3s",
            "QQ", "QJs", "QJo", "QTs", "QTo", "Q9s", "Q8s", "Q7s", "Q6s", "Q5s",
            "JJ", "JTs", "JTo", "J9s", "J8s", "J7s",
            "TT", "T9s", "T8s", "T7s",
            "99", "98s", "97s",
            "88", "87s",
            "77", "66", "55", "44", "33", "22"
        )),
        "BTN" to Range(setOf(
            "AA", "AKs", "AKo", "AQs", "AQo", "AJs", "AJo", "ATs", "ATo", "A9s", "A9o", "A8s", "A8o", "A7s", "A7o", "A6s", "A6o", "A5s", "A5o", "A4s", "A4o", "A3s", "A2s",
            "KK", "KQs", "KQo", "KJs", "KJo", "KTs", "KTo", "K9s", "K9o", "K8s", "K7s", "K6s", "K5s", "K4s", "K3s", "K2s",
            "QQ", "QJs", "QJo", "QTs", "QTo", "Q9s", "Q9o", "Q8s", "Q7s", "Q6s", "Q5s", "Q4s", "Q3s", "Q2s",
            "JJ", "JTs", "JTo", "J9s", "J9o", "J8s", "J7s", "J6s", "J5s", "J4s",
            "TT", "T9s", "T9o", "T8s", "T8o", "T7s", "T6s",
            "99", "98s", "98o", "97s", "96s",
            "88", "87s", "86s", "85s",
            "77", "76s", "75s",
            "66", "65s", "64s",
            "55", "54s",
            "44", "33", "22"
        )),
        "SB" to Range(setOf(
            "AA", "AKs", "AKo", "AQs", "AQo", "AJs", "AJo", "ATs", "ATo", "A9s", "A9o", "A8s", "A8o", "A7s", "A7o", "A6s", "A6o", "A5s", "A5o", "A4s", "A4o", "A3s", "A3o", "A2s",
            "KK", "KQs", "KQo", "KJs", "KJo", "KTs", "KTo", "K9s", "K9o", "K8s", "K8o", "K7s", "K6s", "K5s", "K4s", "K3s", "K2s",
            "QQ", "QJs", "QJo", "QTs", "QTo", "Q9s", "Q9o", "Q8s", "Q7s", "Q6s", "Q5s", "Q4s", "Q3s", "Q2s",
            "JJ", "JTs", "JTo", "J9s", "J9o", "J8s", "J7s", "J6s", "J5s", "J4s", "J3s",
            "TT", "T9s", "T9o", "T8s", "T8o", "T7s", "T6s",
            "99", "98s", "98o", "97s", "96s",
            "88", "87s", "86s", "85s",
            "77", "76s", "75s",
            "66", "65s", "64s",
            "55", "54s", "53s",
            "44", "33", "22"
        )),

        //BB does not RFI
    )
}