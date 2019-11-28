package com.example.empresas_android.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import com.example.empresas_android.Constants
import com.example.empresas_android.R
import com.example.empresas_android.base.App
import com.example.empresas_android.data.local.MyHeaders
import com.example.empresas_android.ui.listingEnterprises.EnterprisesActivity
import com.google.gson.Gson

class SplashScreenActivity : BaseActivity() {
    private lateinit var mySharedPreferences: SharedPreferences
    private lateinit var myHeaders: MyHeaders


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        App.initPreferences(this)
        initPreference()

        goToNextPage()
    }


    private fun goToNextPage() {
        if(hasPreferences()) {
            openActivityAfterTimeAndFinish(EnterprisesActivity::class.java, Constants.SplashScreen.DELAY_MILLIS)
        } else {
            openActivityAfterTimeAndFinish(LoginActivity::class.java, Constants.SplashScreen.DELAY_MILLIS)
        }
    }


    private fun initPreference() {
        mySharedPreferences = getSharedPreferences(getString(R.string.login_key), Context.MODE_PRIVATE)
    }

    private fun hasPreferences(): Boolean {
        val gson = Gson()

        mySharedPreferences.getString(R.string.login_key.toString(), MODE_PRIVATE.toString())

        val myHeadersJson = mySharedPreferences.getString(Constants.IntentBundle.MY_HEADERS, "")


        if (!myHeadersJson.isNullOrEmpty()) {
            myHeaders = gson.fromJson(myHeadersJson, MyHeaders::class.java)
            return true
        }
        return false

    }

}
