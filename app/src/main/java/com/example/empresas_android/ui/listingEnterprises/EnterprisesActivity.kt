package com.example.empresas_android.ui.listingEnterprises

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.empresas_android.R
import com.example.empresas_android.base.App
import com.example.empresas_android.presentation.EnterprisesViewModel
import com.example.empresas_android.ui.BaseActivity
import com.example.empresas_android.ui.EnterpriseDetailActivity
import com.example.empresas_android.ui.LoginActivity
import kotlinx.android.synthetic.main.activity_enterprises.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EnterprisesActivity : BaseActivity() {

    private lateinit var viewModel: EnterprisesViewModel
    private lateinit var adapter : ListingEnterprisesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enterprises)
        initViews()
        viewModel = ViewModelProviders.of(this)[EnterprisesViewModel::class.java]
        createEnterpriseAdapter()
        createObserver()

    }

    private fun initViews() {
        title = ""
        setSupportActionBar(findViewById(R.id.tool_bar))

        logout.setOnClickListener {
            App.clearCredentials()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
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

                enterprisesProgressBar.visibility = View.GONE
            })

        viewModel.getErrorConnection.observe(this,
            Observer {
                enterprisesProgressBar.visibility = View.GONE

                callAlert("Erro na conexão", "Verifique sua conexão com a internet")
            })

        viewModel.getErrorUnauthorized.observe(this,
                Observer {
                    App.clearCredentials()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()

                }
            )

    }


    private fun createEnterpriseAdapter() {

        adapter =
            ListingEnterprisesAdapter { itemEnterprise ->
                val intent = Intent(
                    this@EnterprisesActivity,
                    EnterpriseDetailActivity::class.java
                )
                intent.putExtra("arg_enterprise_id", itemEnterprise.id.toString())
                startActivity(intent)
            }


        enterprisesProgressBar.visibility = View.VISIBLE
        GlobalScope.launch {
            if(hasInternetConnection()){
                viewModel.getEnterprises()
            } else {
                callAlert("Erro na conexão", "Verifique sua conexão com a internet")
            }
        }


    }
}
