package com.example.empresas_android.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.empresas_android.ListingEnterprisesActivity
import com.example.empresas_android.R
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

        viewModel.loginLiveData.observe(this, Observer {
            startActivity(Intent(this, ListingEnterprisesActivity::class.java))
        })

        viewModel.errorLoginLiveData.observe(this, Observer {
            Toast.makeText(this, "Usuário ou senha inválido", Toast.LENGTH_LONG).show()
        })
    }


}
