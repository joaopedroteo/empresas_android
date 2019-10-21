package com.example.empresas_android

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.empresas_android.viewModel.ListingEnterprisesViewModel
import kotlinx.android.synthetic.main.activity_listing_enterprises.*

class ListingEnterprisesActivity : AppCompatActivity() {

    private lateinit var viewModel: ListingEnterprisesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing_enterprises)
        initViews()
        viewModel = ViewModelProviders.of(this)[ListingEnterprisesViewModel::class.java]
        createEnterpriseAdapter()
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
                Toast.makeText(applicationContext, query, Toast.LENGTH_LONG).show()

                return true
            }
        })
    }


    private fun initViews() {
        title = ""
        setSupportActionBar(findViewById(R.id.tool_bar))
    }


    private fun createEnterpriseAdapter() {
        val adapter = ListingEnterprisesAdapter { itemEnterprise ->
            val intent = Intent(
                this@ListingEnterprisesActivity,
                EnterpriseDetailActivity::class.java
            )
            intent.putExtra("arg_enterprise_name", itemEnterprise.name)
            intent.putExtra("arg_enterprise_area", itemEnterprise.area)
            intent.putExtra("arg_enterprise_country", itemEnterprise.country)
            intent.putExtra("arg_enterprise_description", itemEnterprise.description)
            intent.putExtra("arg_enterprise_image", itemEnterprise.image)
            startActivity(intent)
        }

        viewModel.getEnterprises().observe(this, Observer<List<ItemEnterprise>> { enterprises ->
            adapter.contentList = enterprises
            recyclerView.adapter = adapter
        })

    }
}
