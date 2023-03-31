package com.example.data.model

import com.google.gson.annotations.SerializedName

data class DataModel (
    @SerializedName("platform")
    val platform: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("level")
    val level: Int,
    @SerializedName("lifetime")
    val lifetime: String
)

