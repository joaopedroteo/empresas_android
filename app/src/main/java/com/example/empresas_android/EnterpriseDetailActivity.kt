package com.example.empresas_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_enterprise_detail.*
import java.util.*

class EnterpriseDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enterprise_detail)
        initViews()
    }

    private fun initViews() {
        val toolBar = findViewById<Toolbar>(R.id.tool_bar)
        setSupportActionBar(toolBar)

        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent?.run {
            supportActionBar?.title = getStringExtra("arg_enterprise_name")?.toUpperCase(Locale.US)
            txtDetailEnterprise.text = getStringExtra("arg_enterprise_description")
//            var image= getStringExtra("arg_enterprise_image")
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
