package com.codsearchengineprofile.data.apiservice

import com.codsearchengineprofile.data.model.ProfileDataModel
import io.reactivex.Single
import retrofit2.http.GET

interface ProfileService {

    @GET("/warzone/")
    fun getDataProfile(): Single<ProfileDataModel>
}
