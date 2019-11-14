package com.example.empresas_android.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.empresas_android.R
import com.example.empresas_android.URL_IMGS
import com.example.empresas_android.data.local.MyHeaders
import com.example.empresas_android.databinding.ActivityEnterpriseDetailBinding
import com.example.empresas_android.presentation.EnterpriseDetailViewModel
import kotlinx.android.synthetic.main.activity_enterprise_detail.*
import java.util.*

class EnterpriseDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: EnterpriseDetailViewModel
    lateinit var binding:ActivityEnterpriseDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this)[EnterpriseDetailViewModel::class.java]

        binding = DataBindingUtil.setContentView(this, R.layout.activity_enterprise_detail)

        setBiding()
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

        enterpriseDetailProgressBar.visibility = View.VISIBLE
        getEnterpriseDetail(headers, id)
    }

    private fun setBiding() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
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

    private fun getEnterpriseDetail(headers: MyHeaders?, id: Int?) {
        if (id != null && headers != null) {
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
