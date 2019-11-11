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
        val accessToken = headers["access-token"]
        val client = headers["client"]
        val uid = headers["uid"]

        val myHeaders = MyHeaders(accessToken.toString(), client.toString(), uid.toString())
        val myHeadersJson = Gson().toJson(myHeaders)

        val editor = preferences.edit()
        editor.putString(R.string.my_headers.toString(), myHeadersJson)
        editor.apply()
    }

    override fun getCredentials(): HashMap<String, String> {
        val credentials = HashMap<String, String>()
        credentials[CONTENT_TYPE] = preferences.getString(
            CONTENT_TYPE, ""
        ) ?: ""
        credentials[ACCESS_TOKEN] = preferences.getString(
            ACCESS_TOKEN, ""
        ) ?: ""
        credentials[CLIENT] = preferences.getString(
            CLIENT, ""
        ) ?: ""
        credentials[PREF_UID] = preferences.getString(
            PREF_UID, ""
        ) ?: ""

        return credentials
    }

    override fun hasValidCredentials(): Boolean {
        return true
//        val token = preferences.getString(CONTENT_TYPE, "")
//        return !token.isNullOrBlank()
    }

}