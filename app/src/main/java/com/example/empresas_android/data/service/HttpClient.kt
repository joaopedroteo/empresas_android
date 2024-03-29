package com.example.empresas_android.data.service

import com.example.empresas_android.BuildConfig
import com.example.empresas_android.URL_BASE
import com.example.empresas_android.data.local.MyHeaders
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class RetrofitAnalizer {

    private lateinit var okHttpLogin: OkHttpClient.Builder
    private lateinit var okHttpClient: OkHttpClient.Builder

    private fun provideLoggingCapableHttpLogin(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE


        okHttpLogin = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                    val original = chain.request()

                    // Request customization: add request headers
                    val requestBuilder = original.newBuilder()
                        .addHeader("Content-Type", "application/json")

                    return chain.proceed(requestBuilder.build())
                }
            })
        return okHttpLogin.build()
    }

    private fun provideLoggingCapableHttpClient(headers: MyHeaders): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE


        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                    val original = chain.request()

                    // Request customization: add request headers
                    val requestBuilder = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("access-token", headers.accessToken)
                        .header("client", headers.client)
                        .header("uid", headers.uid)

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

    fun loginService(): UserService =
        provideRetrofit(provideLoggingCapableHttpLogin()).create(UserService::class.java)

    fun userService(headers: MyHeaders): UserService =
        provideRetrofit(provideLoggingCapableHttpClient(headers)).create(UserService::class.java)
}