package com.bwang.journeyandroid.data

import com.google.gson.annotations.SerializedName

data class JourneyDTOItem(
    @SerializedName(value = "body")
    val content: String,
    val id: Int,
    val title: String,
    val userId: Int
)