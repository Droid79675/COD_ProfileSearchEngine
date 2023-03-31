package com.example.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val newURL = original.url.newBuilder()
            .addQueryParameter("X-RapidAPI-Key", "e948b7ab50msh0b82a6ea9e95255p16f9bdjsn73b7b5dfd93e")
            .addQueryParameter("X-RapidAPI-Host", "call-of-duty-modern-warfare.p.rapidapi.com")
            .build()

        return chain.proceed(
            original.newBuilder()
                .url(newURL)
                .build()
        )
    }
}
