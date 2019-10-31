package com.example.empresas_android.ui

import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.empresas_android.R
import com.example.empresas_android.URL_IMGS
import com.example.empresas_android.data.local.MyHeaders
import com.example.empresas_android.data.service.model.EnterpriseResponse
import com.example.empresas_android.presentation.EnterpriseDetailViewModel
import kotlinx.android.synthetic.main.activity_enterprise_detail.*
import kotlinx.android.synthetic.main.item_enterprise.*
import java.util.*
import kotlin.random.Random

class EnterpriseDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: EnterpriseDetailViewModel
    private lateinit var enterprise: EnterpriseResponse


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enterprise_detail)
        viewModel = ViewModelProviders.of(this)[EnterpriseDetailViewModel::class.java]
        initViews()
        createObserver()
    }

    private fun initViews() {
        val toolBar = findViewById<Toolbar>(R.id.tool_bar)
        setSupportActionBar(toolBar)

        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val headers = intent.extras?.getParcelable<MyHeaders>("arg_headers")
        val id = intent?.getStringExtra("arg_enterprise_id")?.toInt()

        getEnterpriseDetail(headers, id)
    }


    private fun createObserver() {
        viewModel.enterprise.observe(this,
            androidx.lifecycle.Observer {
                enterprise ->
                supportActionBar?.title = enterprise.enterprise_name.toUpperCase(Locale.US)
                txtDetailEnterprise.text = enterprise.description
                val urlImg = URL_IMGS.elementAt(enterprise.description.length % URL_IMGS.size)
                getImage(urlImg)
            })
    }

    private fun getEnterpriseDetail(headers: MyHeaders?, id:Int?) {
        if(id != null && headers != null) {
            viewModel.getEnterpriseDetail(headers, id)
        } else {
            Toast.makeText(this, "Não foi possível buscar os dados", Toast.LENGTH_LONG).show()
        }
    }

    private fun getImage(photo: String) {
        Glide.with(this).load(photo).placeholder(R.drawable.img_e_1).into(imgEnterprise)
    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
