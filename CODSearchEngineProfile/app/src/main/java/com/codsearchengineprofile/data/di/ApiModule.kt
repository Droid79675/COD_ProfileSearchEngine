package com.codsearchengineprofile.data.di

import com.codsearchengineprofile.data.apiservice.ProfileService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
class ApiModule {

    @Provides
    fun bindApiService(retrofit: Retrofit): ProfileService {
        return retrofit.create(ProfileService::class.java)
    }

}
