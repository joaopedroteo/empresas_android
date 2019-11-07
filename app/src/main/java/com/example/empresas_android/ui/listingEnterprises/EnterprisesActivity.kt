package com.example.empresas_android.ui.listingEnterprises

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.empresas_android.R
import com.example.empresas_android.data.local.MyHeaders
import com.example.empresas_android.presentation.EnterprisesViewModel
import com.example.empresas_android.ui.EnterpriseDetailActivity
import com.example.empresas_android.ui.LoginActivity
import kotlinx.android.synthetic.main.activity_enterprises.*

class EnterprisesActivity : AppCompatActivity() {

    private lateinit var viewModel: EnterprisesViewModel
    private lateinit var adapter : ListingEnterprisesAdapter
    private lateinit var mySharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enterprises)
        initViews()
        viewModel = ViewModelProviders.of(this)[EnterprisesViewModel::class.java]
        createEnterpriseAdapter()
        createObserver()
        initPreference()
    }

    private fun initViews() {
        title = ""
        setSupportActionBar(findViewById(R.id.tool_bar))

        logout.setOnClickListener {
            clearPreference()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun initPreference() {
        mySharedPreferences = getSharedPreferences(getString(R.string.login_key), Context.MODE_PRIVATE)
    }

    private fun callAlert(title: String, message: String = "") {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("Ok") { _, _ ->
        }
        alertDialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        configureSearchView(menu)

        return super.onCreateOptionsMenu(menu)
    }

    private fun configureSearchView(menu: Menu?) {
        val searchView: SearchView = menu?.findItem(R.id.search_view)?.actionView as SearchView
        searchView.queryHint = getString(R.string.search)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchEnterprises(query.toString())
                return true
            }
        })
    }

    private fun createObserver() {

        viewModel.enterprises.observe(this,
            Observer {
                    enterprises ->
                adapter.contentList = enterprises
                recyclerView.adapter = adapter
            })

        viewModel.getErrorConnection.observe(this,
            Observer {
                callAlert("Erro na conexão", "Verifique sua conexão com a internet")
            })

        viewModel.getErrorUnauthorized.observe(this,
                Observer {
                    clearPreference()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()

                }
            )

    }

    private fun clearPreference() {
        mySharedPreferences.getString(R.string.login_key.toString(), MODE_PRIVATE.toString())
        val editor = mySharedPreferences.edit()
        editor.clear()
        editor.apply()
    }


    private fun createEnterpriseAdapter() {
        val headers = intent.extras?.getParcelable<MyHeaders>("arg_headers")

        adapter =
            ListingEnterprisesAdapter { itemEnterprise ->
                val intent = Intent(
                    this@EnterprisesActivity,
                    EnterpriseDetailActivity::class.java
                )
                intent.putExtra("arg_enterprise_id", itemEnterprise.id.toString())
                intent.putExtra("arg_headers", headers)
                startActivity(intent)
            }

        if (headers != null) {
            viewModel.getEnterprises(headers)
        }

    }
}
