package com.example.empresas_android.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import com.example.empresas_android.*
import com.example.empresas_android.data.local.MyHeaders
import com.google.gson.Gson
import okhttp3.Headers

class MyPreferences(private val context: Context) : PreferencesRepository {


    private val preferences:SharedPreferences
     get() = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)


    override fun setCredentials(headers: Headers) {
        val accessToken = headers[ACCESS_TOKEN]
        val client = headers[CLIENT]
        val uid = headers[PREF_UID]

        val myHeaders = MyHeaders(accessToken.toString(), client.toString(), uid.toString())
        val myHeadersJson = Gson().toJson(myHeaders)

        val editor = preferences.edit()
        editor.putString(MY_HEADERS, myHeadersJson)
        editor.apply()
    }

    override fun getCredentials(): HashMap<String, String> {
        val credentials = HashMap<String, String>()

        val headers = preferences.getString(MY_HEADERS, "")
        val myHeaders = Gson().fromJson(headers, MyHeaders::class.java)

        credentials[CONTENT_TYPE] = preferences.getString(
            CONTENT_TYPE, ""
        ) ?: ""

        if (myHeaders != null) {
            credentials[ACCESS_TOKEN] = myHeaders.accessToken
            credentials[CLIENT] = myHeaders.client
            credentials[PREF_UID] = myHeaders.uid
        } else {
            credentials[ACCESS_TOKEN] = ""
            credentials[CLIENT] = ""
            credentials[PREF_UID] = ""
        }

        return credentials
    }

    override fun hasValidCredentials(): Boolean {
        return true
//        val token = preferences.getString(CONTENT_TYPE, "")
//        return !token.isNullOrBlank()
    }

}