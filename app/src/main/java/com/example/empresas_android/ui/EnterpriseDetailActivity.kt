package com.example.empresas_android.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.empresas_android.Constants
import com.example.empresas_android.R
import com.example.empresas_android.URL_IMGS
import com.example.empresas_android.data.local.preferences.MyPreferences
import com.example.empresas_android.databinding.ActivityEnterpriseDetailBinding
import com.example.empresas_android.presentation.EnterpriseDetailViewModel
import com.example.empresas_android.presentation.viewModelFactory.EnterpriseDetailViewModelFactory
import kotlinx.android.synthetic.main.activity_enterprise_detail.*

class EnterpriseDetailActivity : BaseActivity() {
    private val idInvalid = -1

    private lateinit var viewModel: EnterpriseDetailViewModel
    private lateinit var binding:ActivityEnterpriseDetailBinding
    private var myPreferences = MyPreferences(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(
            this,
            EnterpriseDetailViewModelFactory(this)
        )[EnterpriseDetailViewModel::class.java]

        binding = DataBindingUtil.setContentView(this, R.layout.activity_enterprise_detail)

        setBiding()
        initViews()
        createObserver()
    }

    private fun initViews() {
        setUpToolBar(findViewById(R.id.tool_bar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        tool_bar.changeToolbarFont()

        setTypeFace(txtDetailEnterprise, "fonts/SourceSansPro-Regular.ttf")

        val id = intent.getIntExtra(Constants.IntentBundle.ENTERPRISE_ID, idInvalid)

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
                showDialog(getString(R.string.connection_error), getString(R.string.message_verify_connection))
            })
    }

    private fun getEnterpriseDetail(myPreferences: MyPreferences, id: Int) {
        when {
            !hasInternetConnection() -> {
                showDialog(getString(R.string.connection_error), getString(R.string.message_verify_connection))
                enterpriseDetailProgressBar.visibility = View.GONE
            }
            id != idInvalid -> viewModel.getEnterpriseDetail(myPreferences, id)
            else -> {
                showDialog(getString(R.string.unknown_error), getString(R.string.message_fetch_data_not_possible))
                enterpriseDetailProgressBar.visibility = View.GONE
            }
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
        viewModel.clearDisposable()
    }
}
