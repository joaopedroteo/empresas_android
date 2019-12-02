package com.example.empresas_android.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.empresas_android.R
import com.example.empresas_android.presentation.LoginViewModel
import com.example.empresas_android.ui.listingEnterprises.EnterprisesActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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
//                showDialog(getString(R.string.error_email_or_password_invalid) )
                showDialog(viewModel.getMessageError() )
            })
    }

    override fun onStart() {
        super.onStart()

        loginEnterButton?.setOnClickListener {
            if(hasInternetConnection()){
                viewModel.login(loginEmailEditText.text.toString(), loginPasswordEditText.text.toString())
            }
        }

        viewModel.loginLiveData.observeFieldsLogin(this, Observer {
            openActivityAndFinish(EnterprisesActivity::class.java)
        })



    }
}
