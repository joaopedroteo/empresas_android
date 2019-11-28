package com.example.empresas_android.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.empresas_android.R
import com.example.empresas_android.presentation.LoginViewModel
import com.example.empresas_android.presentation.factory.LoginViewModelFactory
import com.example.empresas_android.ui.listingEnterprises.EnterprisesActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(
            this,
            LoginViewModelFactory(this)
        ).get(LoginViewModel::class.java)

//        viewModel = ViewModelProviders.of(this)[LoginViewModel::class.java]
        createObserver()
    }


    private fun createObserver() {
        viewModel.errorMessageIndex.observe(this,
            Observer {
                message ->
                txtErrorEmailOrPassword.text = getString(message)
                txtErrorEmailOrPassword.visibility = View.VISIBLE
            }
        )

        viewModel.errorConnection.observeFieldsLogin(this,
            Observer {
                showDialog(getString(R.string.connection_error), getString(R.string.message_verify_connection))
            })
    }

    override fun onStart() {
        super.onStart()

        btnLogin?.setOnClickListener {
            if(hasInternetConnection()){
                GlobalScope.launch {
                    viewModel.login(edtEmail.text.toString(), edtPassword.text.toString())
                }
            }
        }

        viewModel.loginLiveData.observeFieldsLogin(this, Observer {
            openActivityAndFinish(EnterprisesActivity::class.java)
        })



    }
}
