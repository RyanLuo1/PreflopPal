package com.ryanluo.prefloppal.data

data class User(
    val email: String = "",
    val uid: String = "",
    val createdAt: Long = System.currentTimeMillis()
) {
    // Required empty constructor for Firebase
    constructor() : this("", "")
}