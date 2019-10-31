package com.example.empresas_android.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.empresas_android.R
import com.example.empresas_android.presentation.LoginViewModel
import com.example.empresas_android.ui.listingEnterprises.ListingEnterprisesActivity
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this)[LoginViewModel::class.java]
    }


    override fun onStart() {
        super.onStart()

        btnLogin?.setOnClickListener {
            viewModel.login(edtEmail.text.toString(), edtPassword.text.toString())
        }

        viewModel.loginLiveData.observeFieldsLogin(this, Observer {
            val intent = Intent(this, ListingEnterprisesActivity::class.java)
            val headers = viewModel.getHeaders()

            intent.putExtra("arg_headers", headers)
            startActivity(intent)
            finish()
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
