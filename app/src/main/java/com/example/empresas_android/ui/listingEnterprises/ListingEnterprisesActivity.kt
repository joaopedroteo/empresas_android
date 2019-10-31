package com.example.empresas_android.ui.listingEnterprises

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.empresas_android.R
import com.example.empresas_android.data.local.MyHeaders
import com.example.empresas_android.presentation.ListingEnterprisesViewModel
import com.example.empresas_android.ui.EnterpriseDetailActivity
import kotlinx.android.synthetic.main.activity_listing_enterprises.*

class ListingEnterprisesActivity : AppCompatActivity() {

    private lateinit var viewModel: ListingEnterprisesViewModel
    private lateinit var adapter : ListingEnterprisesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing_enterprises)
        initViews()
        viewModel = ViewModelProviders.of(this)[ListingEnterprisesViewModel::class.java]
        createEnterpriseAdapter()
        createObserver()
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
    }


    private fun initViews() {
        title = ""
        setSupportActionBar(findViewById(R.id.tool_bar))
    }


    private fun createEnterpriseAdapter() {
        val headers = intent.extras?.getParcelable<MyHeaders>("arg_headers")

        adapter =
            ListingEnterprisesAdapter { itemEnterprise ->
                val intent = Intent(
                    this@ListingEnterprisesActivity,
                    EnterpriseDetailActivity::class.java
                )
                intent.putExtra("arg_enterprise_id", itemEnterprise.id.toString())
                intent.putExtra("arg_headers", headers)
                intent.putExtra("arg_enterprise_name", itemEnterprise.enterprise_name)
                intent.putExtra("arg_enterprise_area", itemEnterprise.city)
                intent.putExtra("arg_enterprise_country", itemEnterprise.country)
                intent.putExtra("arg_enterprise_description", itemEnterprise.description)
                intent.putExtra("arg_enterprise_image", itemEnterprise.photo)
                startActivity(intent)
            }



        if (headers != null) {
            viewModel.getEnterprises(headers)
            Log.d("DEBUG", "pegou as empresas")

        }

    }
}
