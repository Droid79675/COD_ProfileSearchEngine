package com.example.data.apiservice

import com.example.data.model.ProfileDataModel
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("/warzone/")
    fun getProfileDomains(): Single<ProfileDataModel>
}
