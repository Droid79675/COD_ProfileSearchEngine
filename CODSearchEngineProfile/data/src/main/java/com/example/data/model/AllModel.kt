package com.example.data.model

import com.google.gson.annotations.SerializedName

data class AllModel (
    @SerializedName("properties")
    val properties: com.example.data.model.PropertiesModel
)
