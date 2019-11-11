package com.example.empresas_android.app

import android.app.Application
import com.example.empresas_android.data.local.preferences.MyPreferences

class App: Application() {
    lateinit var preferencesManager: MyPreferences

    companion object {
        lateinit var instance: App
    }

//    init {
//        instance = this
//        initPreferences()
//    }

    fun getInstance() : App {
        instance = App()
        return instance
    }
//
//    override fun onCreate() {
//        super.onCreate()
//        instance = this
//        initPreferences()
//    }

//    private fun initPreferences() {
//        MyPreferences().initializePreferences(instance.getInstance())
//        preferencesManager = MyPreferences().getInstance()
//    }
}