package com.codsearchengineprofile.data.di.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val btl: Boolean = true
        val psn: Boolean = true
        val xbl: Boolean = true
        val builder = original.newBuilder()
        builder.addHeader("X-RapidAPI-Key", "e948b7ab50msh0b82a6ea9e95255p16f9bdjsn73b7b5dfd93e")
        builder.addHeader("X-RapidAPI-Host", "call-of-duty-modern-warfare.p.rapidapi.com")
        var newURL = original.url.newBuilder()
            .build()
        if(btl == true){
            val btlCode: String = ""
            val btlNickName: String = "" + "%23" + btlCode
            newURL = original.url.newBuilder()
                .addQueryParameter("", btlNickName)
                .build()
        }
        if(psn == true){
            val psnNickName: String = ""
            newURL = original.url.newBuilder()
                .addQueryParameter("", psnNickName)
                .addQueryParameter("", "psn")
                .build()
        }
        if(xbl == true){
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
