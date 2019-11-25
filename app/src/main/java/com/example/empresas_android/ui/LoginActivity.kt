package com.example.empresas_android.ui

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.empresas_android.R
import com.example.empresas_android.presentation.LoginViewModel
import com.example.empresas_android.presentation.viewModelFactory.LoginViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

//    @Inject
//    lateinit var info: Info

    private lateinit var viewModel: LoginViewModel
    private lateinit var mySharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProviders.of(
            this,
            LoginViewModelFactory(this)
        ).get(LoginViewModel::class.java)

//        DaggerInfoComponent.builder().build().inject(this)

        initViews()
        initPreference()
        createObserver()
    }

    private fun initViews() {

        var typeFace = Typeface.createFromAsset(assets, "fonts/Roboto-Bold.ttf")
        loginWelcomeTextView.typeface = typeFace

        typeFace = Typeface.createFromAsset(assets, "fonts/Roboto-Regular.ttf")
        loginDescriptionTextView.typeface = typeFace
        edtEmail.typeface = typeFace
        edtPassword.typeface = typeFace

        typeFace = Typeface.createFromAsset(assets, "fonts/Roboto-Black.ttf")
        btnLogin.typeface = typeFace


        btnLogin?.setOnClickListener {
            if (hasInternetConnection()) {
                viewModel.login(edtEmail.text.toString(), edtPassword.text.toString())
            } else {
                showDialog(
                    getString(R.string.connection_error),
                    getString(R.string.message_verify_connection)
                )
            }
        }
    }

    private fun initPreference() {
        mySharedPreferences =
            getSharedPreferences(getString(R.string.login_key), Context.MODE_PRIVATE)
    }

    private fun createObserver() {

        viewModel.errorMessageIndex.observe(this,
            Observer { message ->
                txtErrorEmailOrPassword.text = getString(message)
                txtErrorEmailOrPassword.visibility = View.VISIBLE
            }
        )

        viewModel.errorConnection.observeFieldsLogin(this,
            Observer {
                showDialog(
                    getString(R.string.connection_error),
                    getString(R.string.message_verify_connection)
                )
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearDisposable()
    }
}
