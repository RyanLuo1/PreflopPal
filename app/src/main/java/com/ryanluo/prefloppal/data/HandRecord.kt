package com.ryanluo.prefloppal.data

data class HandRecord(
    var id: String = "",  // Must be var to allow Firebase to set it
    val card1: String = "",
    val card2: String = "",
    val position: String = "",
    val previousAction: String = "",
    val advice: String = "",
    val timestamp: Long = System.currentTimeMillis()
) {
    // Required empty constructor for Firebase
    constructor() : this("", "", "", "", "", "", 0L)
}