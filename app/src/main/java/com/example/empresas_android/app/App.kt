package com.example.empresas_android.app

import android.app.Application
import android.content.Context
import com.example.empresas_android.data.local.preferences.MyPreferences

class App: Application() {

    companion object {
        lateinit var myPreferences: MyPreferences

        fun initPreferences(context: Context) {
            myPreferences = MyPreferences(context)
        }

        fun getPreferences(): MyPreferences {
            return myPreferences
        }

        fun clearCredentials() {
            myPreferences.clearCredentials()
        }
    }

}