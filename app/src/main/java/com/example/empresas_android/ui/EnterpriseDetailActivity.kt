package com.example.empresas_android.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.empresas_android.ARG_ENTERPRISE_ID
import com.example.empresas_android.R
import com.example.empresas_android.URL_IMGS
import com.example.empresas_android.data.local.preferences.MyPreferences
import com.example.empresas_android.databinding.ActivityEnterpriseDetailBinding
import com.example.empresas_android.presentation.EnterpriseDetailViewModel
import kotlinx.android.synthetic.main.activity_enterprise_detail.*

class EnterpriseDetailActivity : BaseActivity() {

    private lateinit var viewModel: EnterpriseDetailViewModel
    private lateinit var binding:ActivityEnterpriseDetailBinding
    private var myPreferences = MyPreferences(this)

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

        val id = intent?.getStringExtra(ARG_ENTERPRISE_ID)?.toInt()

        getEnterpriseDetail(myPreferences, id)
    }

    private fun setBiding() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun createObserver() {

        viewModel.getProgressBar.observe(this,
            Observer {
                enterpriseDetailProgressBar.visibility = if(it) View.VISIBLE else View.GONE
            })

        viewModel.enterprise.observe(this,
            androidx.lifecycle.Observer { enterprise ->
                val urlImg = URL_IMGS.elementAt(enterprise.description.length % URL_IMGS.size)
                getImage(urlImg)
            })

        viewModel.getErrorConnection.observe(this,
            androidx.lifecycle.Observer {
                callAlert(getString(R.string.connection_error), getString(R.string.message_verify_connection))
            })
    }

    private fun getEnterpriseDetail(myPreferences: MyPreferences, id: Int?) {
        if (id != null) {
            viewModel.getEnterpriseDetail(myPreferences, id)
        } else {
            callAlert(getString(R.string.unknown_error), getString(R.string.message_fetch_data_not_possible))
        }
    }

    private fun getImage(photo: String) {
        Glide.with(this).load(photo).placeholder(R.drawable.img_e_1).into(imgEnterprise)
    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }
}
