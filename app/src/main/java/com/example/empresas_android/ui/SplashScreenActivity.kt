package com.example.empresas_android.ui

import android.os.Bundle
import com.example.empresas_android.Constants
import com.example.empresas_android.R
import com.example.empresas_android.base.App
import com.example.empresas_android.ui.listingEnterprises.EnterprisesActivity

class SplashScreenActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        App.initPreferences(this)

        goToNextPage()
    }


    private fun goToNextPage() {
        if(hasPreferences()) {
            openActivityAfterTimeAndFinish(EnterprisesActivity::class.java, Constants.SplashScreen.DELAY_MILLIS)
        } else {
            openActivityAfterTimeAndFinish(LoginActivity::class.java, Constants.SplashScreen.DELAY_MILLIS)
        }
    }

    private fun hasPreferences(): Boolean {
        return App.hasValidCredentials()
    }

}
