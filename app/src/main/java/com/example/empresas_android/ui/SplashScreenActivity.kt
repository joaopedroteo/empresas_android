package com.example.empresas_android.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.example.empresas_android.DELAY_MILLIS
import com.example.empresas_android.MY_HEADERS
import com.example.empresas_android.PREF_KEY
import com.example.empresas_android.R
import com.example.empresas_android.data.local.MyHeaders
import com.example.empresas_android.ui.listingEnterprises.EnterprisesActivity
import com.google.gson.Gson

class SplashScreenActivity : BaseActivity() {
    private lateinit var mySharedPreferences: SharedPreferences
    private lateinit var myHeaders: MyHeaders


    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_splash_screen)
        initPreference()

        goToNextPage()
    }


    private fun goToNextPage() {
        if(hasPreferences() && hasInternetConnection()) {
            openActivityAfterTimeAndFinish(EnterprisesActivity::class.java, DELAY_MILLIS)
        } else {
            openActivityAfterTimeAndFinish(LoginActivity::class.java, DELAY_MILLIS)
        }
    }

    private fun initPreference() {
        mySharedPreferences = getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
    }

    private fun hasPreferences(): Boolean {
        val gson = Gson()

        val myHeadersJson = mySharedPreferences.getString(MY_HEADERS, "")


        if (!myHeadersJson.isNullOrEmpty()) {
            myHeaders = gson.fromJson(myHeadersJson, MyHeaders::class.java)

            return true
        }
        return false
    }


}
