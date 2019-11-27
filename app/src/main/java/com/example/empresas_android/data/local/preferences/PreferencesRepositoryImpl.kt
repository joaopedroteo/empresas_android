package com.example.empresas_android.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import com.example.empresas_android.Constants
import com.example.empresas_android.data.local.MyHeaders
import com.google.gson.Gson
import okhttp3.Headers

class PreferencesRepositoryImpl(private val context: Context) : PreferencesRepository {

    private val preferences: SharedPreferences
    get() = context.getSharedPreferences(Constants.SharedPreferences.PREF_KEY, Context.MODE_PRIVATE)


    override fun saveCredentials(headers: Headers) {
        val accessToken = headers[Constants.SharedPreferences.ACCESS_TOKEN]
        val client = headers[Constants.SharedPreferences.CLIENT]
        val uid = headers[Constants.SharedPreferences.PREF_UID]

        val myHeaders = MyHeaders(accessToken.toString(), client.toString(), uid.toString())
        val myHeadersJson = Gson().toJson(myHeaders)

        val editor = preferences.edit()
        editor.putString(Constants.IntentBundle.MY_HEADERS, myHeadersJson)
        editor.apply()
    }

    override fun getCredentials(): HashMap<String, String> {
        val credentials = HashMap<String, String>()

        val headers = preferences.getString(Constants.IntentBundle.MY_HEADERS, "")
        val myHeaders = Gson().fromJson(headers, MyHeaders::class.java)

        credentials[Constants.SharedPreferences.CONTENT_TYPE] = preferences.getString(
            Constants.SharedPreferences.CONTENT_TYPE, ""
        ) ?: ""

        if (myHeaders != null) {
            credentials[Constants.SharedPreferences.ACCESS_TOKEN] = myHeaders.accessToken
            credentials[Constants.SharedPreferences.CLIENT] = myHeaders.client
            credentials[Constants.SharedPreferences.PREF_UID] = myHeaders.uid
        } else {
            credentials[Constants.SharedPreferences.ACCESS_TOKEN] = ""
            credentials[Constants.SharedPreferences.CLIENT] = ""
            credentials[Constants.SharedPreferences.PREF_UID] = ""
        }

        return credentials
    }

    override fun deleteCredentials() {
        val editor = preferences.edit()
        editor.clear().apply()
    }


    override fun hasValidCredentials(): Boolean {
        val token = preferences.getString(Constants.IntentBundle.MY_HEADERS, "")
        return !token.isNullOrBlank()
    }


}