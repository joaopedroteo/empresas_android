package com.example.empresas_android.data.service

import com.example.empresas_android.BuildConfig
import com.example.empresas_android.URL_BASE
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import okhttp3.logging.HttpLoggingInterceptor

class RetrofitAnalizer {


    private lateinit var okHttpClient: OkHttpClient.Builder

    private fun provideLoggingCapableHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        okHttpClient =  OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                    val original = chain.request()

                    // Request customization: add request headers
                    val requestBuilder = original.newBuilder()
                        .addHeader("Content-Type", "application/json") // <-- this is the important line

                    return chain.proceed(requestBuilder.build())
                }
            })

        return okHttpClient.build()
    }


    private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun userService(): UserService = provideRetrofit(provideLoggingCapableHttpClient()).create(UserService::class.java)

}