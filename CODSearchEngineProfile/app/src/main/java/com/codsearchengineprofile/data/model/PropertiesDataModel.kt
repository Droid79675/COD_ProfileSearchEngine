package com.codsearchengineprofile.data.model

import com.google.gson.annotations.SerializedName

data class PropertiesDataModel (
    @SerializedName("accuracy")
    val accuracy: Double,
    @SerializedName("wins")
    val wins: Int,
    @SerializedName("losses")
    val losses: Int,
    @SerializedName("kdRatio")
    val kdRatio: Double,
    @SerializedName("headshots")
    val headshots: Int,
    @SerializedName("score")
    val score: Long
)
