package com.example.empresas_android.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.empresas_android.R
import com.example.empresas_android.data.local.MyHeaders
import com.example.empresas_android.presentation.LoginViewModel
import com.example.empresas_android.ui.listingEnterprises.EnterprisesActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var myHeaders: MyHeaders
    private lateinit var mySharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this)[LoginViewModel::class.java]
        initPreference()
        createObserver()
    }

    private fun initPreference() {
        mySharedPreferences =
            getSharedPreferences(getString(R.string.login_key), Context.MODE_PRIVATE)
    }


    private fun savePreference() {
        val gson = Gson()
        val myHeadersJson = gson.toJson(myHeaders)

        mySharedPreferences.getString(R.string.login_key.toString(), MODE_PRIVATE.toString())
        val editor = mySharedPreferences.edit()
        editor.putString(R.string.my_headers.toString(), myHeadersJson)
        editor.apply()
    }

    private fun goToNextPage() {
        val intent = Intent(this, EnterprisesActivity::class.java)
        intent.putExtra("arg_headers", myHeaders)
        startActivity(intent)
        finish()
    }

    private fun callAlert(title: String, message: String) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("Ok") { _, _ ->
        }
        alertDialog.show()
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
                callAlert("Erro na conexão", "Verifique sua conexão com a internet")
            })
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



    }
}
