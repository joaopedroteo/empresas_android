package com.example.empresas_android.base

import android.app.Application
import android.content.Context
import com.example.empresas_android.data.local.preferences.PreferencesRepositoryImpl
import com.example.empresas_android.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class App: Application() {

    companion object {
        private lateinit var myPreferences: PreferencesRepositoryImpl

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

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(generalModule, viewModelModule, useCasesModule, repositoryModule, webServiceModule)
            )
        }
    }

}