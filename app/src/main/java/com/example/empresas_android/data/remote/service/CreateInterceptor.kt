package com.example.empresas_android.data.remote.service

import okhttp3.Interceptor
import okhttp3.Response

class CreateInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()

            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
                .addHeader("Content-Type", "application/json")

            return chain.proceed(requestBuilder.build())
    }
}