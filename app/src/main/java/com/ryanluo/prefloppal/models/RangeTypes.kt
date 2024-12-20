package com.ryanluo.prefloppal.models

import com.ryanluo.prefloppal.utils.Range

data class RFIThreeBetRange(
    val valueRange: Range,
    val bluffRange: Range
)

data class FacingThreeBetRange(
    val callRange: Range,
    val fourBetValueRange: Range,
    val fourBetBluffRange: Range
)