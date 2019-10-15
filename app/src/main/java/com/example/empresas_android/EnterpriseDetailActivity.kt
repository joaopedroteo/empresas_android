package com.example.empresas_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_enterprise_detail.*
import kotlinx.android.synthetic.main.custom_edit_text.view.*

class EnterpriseDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enterprise_detail)
        var toolBar = findViewById<Toolbar>(R.id.tool_bar)
        setSupportActionBar(toolBar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent?.run {
            supportActionBar?.title = getStringExtra("arg_enterprise_name")
            txtDetailEnterprise.text = getStringExtra("arg_enterprise_description")
            var image= getStringExtra("arg_enterprise_image")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
