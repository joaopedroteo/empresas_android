package com.example.empresas_android.base

import android.app.Application
import android.content.Context
import com.example.empresas_android.data.local.preferences.PreferencesRepositoryImpl

class App: Application() {

    companion object {
        lateinit var myPreferences: PreferencesRepositoryImpl

        fun initPreferences(context: Context) {
            myPreferences = PreferencesRepositoryImpl(context)
        }

        fun getPreferences(): PreferencesRepositoryImpl {
            return myPreferences
        }

        fun clearCredentials() {
            myPreferences.deleteCredentials()
        }
    }

}