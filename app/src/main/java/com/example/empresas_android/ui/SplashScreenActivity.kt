package com.example.empresas_android.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.empresas_android.R
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
            { goToNextPage() }, 1500)
        //delayMillis pode ser uma constante
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
        mySharedPreferences = getSharedPreferences(getString(R.string.login_key), Context.MODE_PRIVATE)
    }

    private fun hasPreferences(): Boolean {
        val gson = Gson()

        mySharedPreferences.getString(R.string.login_key.toString(), MODE_PRIVATE.toString())

        val myHeadersJson = mySharedPreferences.getString(R.string.my_headers.toString(), "")


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
        intent.putExtra("arg_headers", myHeaders)
        // arg_headers deve ser uma constante
        startActivity(intent)
        finish()
    }

}
