package com.example.empresas_android.data.remote.service

import android.util.Log
import com.example.empresas_android.BuildConfig
import com.example.empresas_android.Constants
import com.example.empresas_android.base.App
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class RetrofitAnalizer {
    private lateinit var okHttpClient: OkHttpClient.Builder

    private val networkEvent: NetworkEvent = NetworkEvent


    private fun provideLoggingCapableHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE


        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                    var request = chain.request()

                    val preferences = App.getPreferences()
                    val credentials = preferences.getCredentials()

                    // Request customization: add request headers
                    request = request.newBuilder()
                        .header(Constants.SharedPreferences.CONTENT_TYPE, Constants.SharedPreferences.APPLICATION_JSON)
                        .header(Constants.SharedPreferences.ACCESS_TOKEN, credentials[Constants.SharedPreferences.ACCESS_TOKEN] ?: "")
                        .header(Constants.SharedPreferences.CLIENT, credentials[Constants.SharedPreferences.CLIENT] ?: "")
                        .header(Constants.SharedPreferences.PREF_UID, credentials[Constants.SharedPreferences.PREF_UID] ?: "")
                        .build()

                    val response = chain.proceed(request)
                    when (response.code) {
                        HttpCodes.OK.value -> preferences.saveCredentials(response.headers)

                        HttpCodes.UNAUTHORIZED.value -> networkEvent.publish(NetworkState.UNAUTHORISED)
                        503 -> networkEvent.publish(NetworkState.NO_RESPONSE)
                    }
                    return response
                }

            })

        return okHttpClient.build()
    }


    private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.Service.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }

    fun userService(): UserService =
        provideRetrofit(provideLoggingCapableHttpClient()).create(UserService::class.java)
}