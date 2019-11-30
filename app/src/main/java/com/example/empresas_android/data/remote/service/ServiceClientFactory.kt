package com.example.empresas_android.data.remote.service

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

object ServiceClientFactory {

    private val networkEvent: NetworkEvent = NetworkEvent

    fun createClient(
        okHttpClient: OkHttpClient,
        coroutineAdapter: CoroutineCallAdapterFactory
    ): UserService {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.Service.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(coroutineAdapter)
            .client(okHttpClient)
            .build()

        return retrofit.create(UserService::class.java)
    }

    fun createOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE


        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                    var request = chain.request()

                    val preferences = App.getPreferences()
                    val credentials = preferences.getCredentials()

                    request = request.newBuilder()
                        .header(
                            Constants.SharedPreferences.CONTENT_TYPE,
                            Constants.SharedPreferences.APPLICATION_JSON
                        )
                        .header(
                            Constants.SharedPreferences.ACCESS_TOKEN,
                            credentials[Constants.SharedPreferences.ACCESS_TOKEN] ?: ""
                        )
                        .header(
                            Constants.SharedPreferences.CLIENT,
                            credentials[Constants.SharedPreferences.CLIENT] ?: ""
                        )
                        .header(
                            Constants.SharedPreferences.PREF_UID,
                            credentials[Constants.SharedPreferences.PREF_UID] ?: ""
                        )
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

}