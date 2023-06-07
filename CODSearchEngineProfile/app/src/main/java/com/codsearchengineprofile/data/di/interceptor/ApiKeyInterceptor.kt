package com.codsearchengineprofile.data.di.interceptor

import com.codsearchengineprofile.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val btl = true// достать с экрана, какую функцию выбрал юзер
        val psn = true
        val xbl = true
        val builder = original.newBuilder()
        builder.addHeader("X-RapidAPI-Key", BuildConfig.API_KEY)
        builder.addHeader("X-RapidAPI-Host", "call-of-duty-modern-warfare.p.rapidapi.com")
        var newURL = original.url.newBuilder()
            .build()
        if (btl) {
            val btlCode: String = ""
            val btlNickName: String = "" + "%23" + btlCode
            newURL = original.url.newBuilder()
                .addQueryParameter("", btlNickName)
                .build()
        }
        if (psn) {
            val psnNickName: String = ""
            newURL = original.url.newBuilder()
                .addQueryParameter("", psnNickName)
                .addQueryParameter("", "psn")
                .build()
        }
        if (xbl) {
            val xblNickName: String = ""
            newURL = original.url.newBuilder()
                .addQueryParameter("", xblNickName)
                .addQueryParameter("", "xbl")
                .build()
        }

        return chain.proceed(
            builder
                .url(newURL)
                .build()
        )
    }
}
