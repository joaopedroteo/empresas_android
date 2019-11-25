package com.example.empresas_android.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.example.empresas_android.Constants
import com.example.empresas_android.R
import com.example.empresas_android.app.App
import com.example.empresas_android.data.local.MyHeaders
import com.example.empresas_android.ui.listingEnterprises.EnterprisesActivity
import com.google.gson.Gson
import javax.inject.Inject

class SplashScreenActivity : BaseActivity() {
    private lateinit var mySharedPreferences: SharedPreferences
    private lateinit var myHeaders: MyHeaders


    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)

        App.initPreferences(this)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_splash_screen)
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
        mySharedPreferences = getSharedPreferences(Constants.SharedPreferences.PREF_KEY, Context.MODE_PRIVATE)
    }

    private fun hasPreferences(): Boolean {
        val gson = Gson()

        val myHeadersJson = mySharedPreferences.getString(Constants.IntentBundle.MY_HEADERS, "")


        if (!myHeadersJson.isNullOrEmpty()) {
            myHeaders = gson.fromJson(myHeadersJson, MyHeaders::class.java)

            return true
        }
        return false
    }


}
