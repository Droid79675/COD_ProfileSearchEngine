package com.codsearchengineprofile.data.di

import com.codsearchengineprofile.BuildConfig
import com.codsearchengineprofile.data.apiservice.ProfileService
import com.codsearchengineprofile.data.di.interceptor.ApiKeyInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

//    private val url: String = "https://call-of-duty-modern-warfare.p.rapidapi.com/"

    @Provides
    fun provideOkHttpClient(@Named("api_key") apiKeyInterceptor: Interceptor): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor(HttpLoggingInterceptor())
        okHttpBuilder.addInterceptor(apiKeyInterceptor)
        return okHttpBuilder.build()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        @Named("base_url") baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Named("api_key")
    fun provideApiKeyInterceptor(): Interceptor = ApiKeyInterceptor()

    @Provides
    @Named("base_url")
    fun provideBaseUrl(): String = BuildConfig.API_ENDPOINT

    @Provides
    fun provideProfileService(
        retrofit: Retrofit
    ): ProfileService = retrofit.create(ProfileService::class.java)
}
