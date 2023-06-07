package com.codsearchengineprofile.data.apiservice

import com.codsearchengineprofile.data.model.ProfileDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfileService {
    //было /warzone/
    @GET("warzone/")
    suspend fun getDataProfile(
        @Query("") username: String,
        @Query("") platform: String
    ): ProfileDataModel
}
