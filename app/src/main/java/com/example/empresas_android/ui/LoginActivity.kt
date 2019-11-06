package com.example.empresas_android.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.empresas_android.R
import com.example.empresas_android.data.local.MyHeaders
import com.example.empresas_android.presentation.LoginViewModel
import com.example.empresas_android.ui.listingEnterprises.ListingEnterprisesActivity
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var mySharedPreferences: SharedPreferences
    private lateinit var myHeaders: MyHeaders

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this)[LoginViewModel::class.java]
        initPreference()
        verifyPreferences()
    }


    private fun verifyPreferences() {
        mySharedPreferences.getString(R.string.login_key.toString(), MODE_PRIVATE.toString())
        val accessToken = mySharedPreferences.getString(R.string.accessToken.toString(), "")
        val client = mySharedPreferences.getString(R.string.client.toString(), "")
        val uid = mySharedPreferences.getString(R.string.uid.toString(), "")
        if (!accessToken.isNullOrEmpty() && !client.isNullOrEmpty() && !uid.isNullOrEmpty()) {
            myHeaders = MyHeaders(accessToken, client, uid)
            goToNextPage()
        }
    }

    private fun savePreference() {
        mySharedPreferences.getString(R.string.login_key.toString(), MODE_PRIVATE.toString())
        val editor = mySharedPreferences.edit()
        editor.putString(R.string.accessToken.toString(), myHeaders.accessToken)
        editor.putString(R.string.client.toString(), myHeaders.client)
        editor.putString(R.string.uid.toString(), myHeaders.uid)
        editor.apply()
    }

    private fun initPreference() {
        mySharedPreferences = getSharedPreferences(getString(R.string.login_key), Context.MODE_PRIVATE)
    }

    private fun goToNextPage() {
        val intent = Intent(this, ListingEnterprisesActivity::class.java)
        intent.putExtra("arg_headers", myHeaders)
        startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()

        btnLogin?.setOnClickListener {
            viewModel.login(edtEmail.text.toString(), edtPassword.text.toString())
        }

        viewModel.loginLiveData.observeFieldsLogin(this, Observer {
            myHeaders = viewModel.getHeaders()
            savePreference()
            goToNextPage()
        })

        viewModel.errorEmptyEmailField.observeFieldsLogin(this, Observer {
            txtErrorInvalidEmail.text = getString(R.string.error_msg_empty_field)
            txtErrorInvalidEmail.visibility = View.VISIBLE
        })

        viewModel.errorEmptyPasswordField.observeFieldsLogin(this, Observer {
            txtErrorPassword.text = getString(R.string.error_msg_empty_field)
            txtErrorPassword.visibility = View.VISIBLE
        })

        viewModel.errorEmailInvalid.observeFieldsLogin(this, Observer {
            txtErrorInvalidEmail.text = getString(R.string.error_msg_invalid_email)
            txtErrorInvalidEmail.visibility = View.VISIBLE
        })

        viewModel.errorHasNotNumberPass.observeFieldsLogin(this, Observer {
            txtErrorPassword.text = getString(R.string.error_msg_password_has_not_number)
            txtErrorPassword.visibility = View.VISIBLE
        })

        viewModel.errorHasNotUpperCase.observeFieldsLogin(this, Observer {
            txtErrorPassword.text = getString(R.string.error_msg_password_has_not_uppercase)
            txtErrorPassword.visibility = View.VISIBLE
        })

        viewModel.errorHasNotLowerCase.observeFieldsLogin(this, Observer {
            txtErrorPassword.text = getString(R.string.error_msg_password_has_not_lowercase)
            txtErrorPassword.visibility = View.VISIBLE
        })

        viewModel.errorHasNotEspecialCharacters.observeFieldsLogin(this, Observer {
            txtErrorPassword.text = getString(R.string.error_msg_password_has_not_especial_character)
            txtErrorPassword.visibility = View.VISIBLE
        })

        viewModel.errorHasNotMinimumSize.observeFieldsLogin(this, Observer {
            txtErrorPassword.text = getString(R.string.error_msg_password_has_not_minimum_size)
            txtErrorPassword.visibility = View.VISIBLE
        })

        viewModel.emailValid.observeFieldsLogin(this, Observer {
            txtErrorInvalidEmail.visibility = View.GONE
        })

        viewModel.passwordValid.observeFieldsLogin(this, Observer {
            txtErrorPassword.visibility = View.GONE
        })
    }
}
