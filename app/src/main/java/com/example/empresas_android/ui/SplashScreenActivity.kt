package com.example.empresas_android.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.empresas_android.*
import com.example.empresas_android.data.local.MyHeaders
import com.example.empresas_android.ui.listingEnterprises.EnterprisesActivity
import com.google.gson.Gson

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var mySharedPreferences: SharedPreferences
    private lateinit var myHeaders: MyHeaders


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        initPreference()

        val handler = Handler()
        handler.postDelayed(
            { goToNextPage() }, DELAY_MILLIS )
    }


    private fun goToNextPage() {
        if(hasPreferences()) {
            goToListingEnterprisesPage()
        } else {
            goToLoginPage()
        }
        finish()
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

    private fun goToLoginPage() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToListingEnterprisesPage() {
        val intent = Intent(this, EnterprisesActivity::class.java)
        intent.putExtra(ARG_HEADERS, myHeaders)
        startActivity(intent)
        finish()
    }

}
