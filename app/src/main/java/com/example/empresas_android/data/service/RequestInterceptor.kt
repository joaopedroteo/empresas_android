package com.example.empresas_android.data.service

import com.example.empresas_android.*
import com.example.empresas_android.app.App
import com.example.empresas_android.data.local.preferences.MyPreferences
import okhttp3.Interceptor
import okhttp3.Response

open class RequestInterceptor : Interceptor {
    private val networkEvent: NetworkEvent = NetworkEvent

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val preferences = App.getPreferences()

        if(preferences.hasValidCredentials()) {
            val credentials = preferences.getCredentials()
            request = request.newBuilder()
                .header(Constants.SharedPreferences.CONTENT_TYPE, Constants.SharedPreferences.APPLICATION_JSON)
                .header(Constants.SharedPreferences.ACCESS_TOKEN, credentials[Constants.SharedPreferences.ACCESS_TOKEN] ?: "")
                .header(Constants.SharedPreferences.CLIENT, credentials[Constants.SharedPreferences.CLIENT] ?: "")
                .header(Constants.SharedPreferences.PREF_UID, credentials[Constants.SharedPreferences.PREF_UID] ?: "")
                .build()

        }

        val response = chain.proceed(request)
        when (response.code) {
            HttpCodes.OK.value -> preferences.setCredentials(response.headers)

            HttpCodes.UNAUTHORIZED.value -> networkEvent.publish(NetworkState.UNAUTHORISED)

            503 -> networkEvent.publish(NetworkState.NO_RESPONSE)
        }
        return response
    }
}
