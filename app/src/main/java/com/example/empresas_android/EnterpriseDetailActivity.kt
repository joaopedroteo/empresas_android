package com.example.empresas_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_enterprise_detail.*

class EnterpriseDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enterprise_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent?.run {
            setTitle(getStringExtra("arg_enterprise_name"))
            txtDetailEnterprise.text = getStringExtra("arg_enterprise_description")
            var image= getStringExtra("arg_enterprise_image")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
