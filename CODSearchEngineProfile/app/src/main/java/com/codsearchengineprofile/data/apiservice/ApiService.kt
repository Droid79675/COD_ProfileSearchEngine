package com.codsearchengineprofile.data.apiservice

import com.codsearchengineprofile.data.model.ProfileDataModel
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("/warzone/")
    fun getProfileDomains(): Single<ProfileDataModel>
}
