package com.codsearchengineprofile.data.model

import com.google.gson.annotations.SerializedName

data class AllDataModel (
    @SerializedName("properties")
    val properties: PropertiesDataModel
)
