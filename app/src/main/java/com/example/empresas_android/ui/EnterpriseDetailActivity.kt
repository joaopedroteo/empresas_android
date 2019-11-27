package com.example.empresas_android.ui

import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.empresas_android.R
import com.example.empresas_android.URL_IMGS
import com.example.empresas_android.presentation.EnterpriseDetailViewModel
import kotlinx.android.synthetic.main.activity_enterprise_detail.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*

class EnterpriseDetailActivity : BaseActivity() {

    private lateinit var viewModel: EnterpriseDetailViewModel

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

        val id = intent?.getStringExtra("arg_enterprise_id")?.toInt()

        enterpriseDetailProgressBar.visibility = View.VISIBLE
        getEnterpriseDetail(id)
    }

    private fun callAlert(title: String, message: String = "") {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("Ok") { _, _ ->
        }
        alertDialog.show()
    }

    private fun createObserver() {
        viewModel.enterprise.observe(this,
            androidx.lifecycle.Observer { enterprise ->
                supportActionBar?.title = enterprise.enterprise_name.toUpperCase(Locale.US)
                txtDetailEnterprise.text = enterprise.description
                val urlImg = URL_IMGS.elementAt(enterprise.description.length % URL_IMGS.size)
                getImage(urlImg)

                enterpriseDetailProgressBar.visibility = View.GONE
            })

        viewModel.getErrorConnection.observe(this,
            androidx.lifecycle.Observer {
                enterpriseDetailProgressBar.visibility = View.GONE

                callAlert("Erro na conexão", "Verifique sua conexão com a internet")
            })
    }

    private fun getEnterpriseDetail(id: Int?) {
        if (id != null) {
            GlobalScope.launch {
                if(hasInternetConnection()){
                    viewModel.getEnterpriseDetail(id)
                } else {
                    callAlert("Erro na conexão", "Verifique sua conexão com a internet")
                }
            }
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
