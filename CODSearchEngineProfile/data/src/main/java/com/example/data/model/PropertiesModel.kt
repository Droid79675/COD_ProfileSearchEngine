package com.example.data.model

import com.google.gson.annotations.SerializedName

data class PropertiesModel (
    @SerializedName("accuracy")
    val accuracy: Double,
    @SerializedName("wins")
    val wins: Int,
    @SerializedName("losses")
    val losses: Int,
    @SerializedName("kdRatio")
    val kdRatio: Double,
    @SerializedName("headshots")
    val headshots: Int
)
