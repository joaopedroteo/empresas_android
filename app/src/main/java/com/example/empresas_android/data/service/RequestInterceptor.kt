package com.example.empresas_android.data.service

import com.example.empresas_android.*
import com.example.empresas_android.data.local.preferences.MyPreferences
import okhttp3.Interceptor
import okhttp3.Response

open class RequestInterceptor (private val myPreferences: MyPreferences) : Interceptor {

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
        myPreferences.setCredentials(response.headers)
        return response
    }

}