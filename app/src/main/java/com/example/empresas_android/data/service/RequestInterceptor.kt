package com.example.empresas_android.data.service

import com.example.empresas_android.*
import com.example.empresas_android.data.local.preferences.MyPreferences
import okhttp3.Interceptor
import okhttp3.Response

open class RequestInterceptor (private val myPreferences: MyPreferences) : Interceptor {
    private val networkEvent: NetworkEvent = NetworkEvent

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if(myPreferences.hasValidCredentials()) {
            val credentials = myPreferences.getCredentials()
            request = request.newBuilder()
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .header(ACCESS_TOKEN, credentials[ACCESS_TOKEN] ?: "")
                .header(CLIENT, credentials[CLIENT] ?: "")
                .header(PREF_UID, credentials[PREF_UID] ?: "")
                .build()

        }

        val response = chain.proceed(request)
        when (response.code) {
            RESPONSE_OK -> myPreferences.setCredentials(response.headers)

            RESPONSE_UNAUTHORIZED -> networkEvent.publish(NetworkState.UNAUTHORISED)

            503 -> networkEvent.publish(NetworkState.NO_RESPONSE)
        }
        return response

    }
}

enum class NetworkState {
    NO_INTERNET, NO_RESPONSE, UNAUTHORISED
}