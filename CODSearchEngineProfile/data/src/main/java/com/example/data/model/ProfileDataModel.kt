package com.example.data.model

import com.google.gson.annotations.SerializedName

data class ProfileDataModel (
    @SerializedName("status")
    val statusCode: String,
    @SerializedName("data")
    val dataModel: ProfileDataModel // В ответе запроса куча объектов внутри друг друга поэтому так много почти пустых классов
)
