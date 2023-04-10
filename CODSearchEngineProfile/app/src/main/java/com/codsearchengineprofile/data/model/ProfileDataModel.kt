package com.codsearchengineprofile.data.model

import com.google.gson.annotations.SerializedName

data class ProfileDataModel (
    @SerializedName("status")
    val statusCode: String,
    @SerializedName("data")
    val dataModel: DataModel
)
