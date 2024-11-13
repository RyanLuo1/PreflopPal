package com.ryanluo.prefloppal.models

import com.ryanluo.prefloppal.utils.Range

data class RFIThreeBetRange(
    val callRange: Range,
    val valueRange: Range,
    val bluffRange: Range,
    val rcRange : Range, // raise/call mix
    val fcRange : Range, // fold/call mix
)

data class FacingThreeBetRange(
    val callRange: Range,
    val rcRange: Range,
    val fourBetValueRange: Range,
    val fourBetBluffRange: Range
)

data class FacingFourBetRange(

    val callRange: Range,
    val allInRange : Range,
    val bluffRange: Range
)