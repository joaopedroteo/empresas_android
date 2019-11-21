package com.example.empresas_android.ui.listingEnterprises

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.empresas_android.Constants
import com.example.empresas_android.R
import com.example.empresas_android.data.local.preferences.MyPreferences
import com.example.empresas_android.data.service.NetworkEvent
import com.example.empresas_android.data.service.NetworkState
import com.example.empresas_android.presentation.EnterprisesViewModel
import com.example.empresas_android.presentation.viewModelFactory.EnterprisesViewModelFactory
import com.example.empresas_android.ui.BaseActivity
import com.example.empresas_android.ui.EnterpriseDetailActivity
import com.example.empresas_android.ui.LoginActivity
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_enterprises.*

class EnterprisesActivity : BaseActivity() {

    private lateinit var viewModel: EnterprisesViewModel
    private lateinit var adapter: ListingEnterprisesAdapter

    private var myPreferences = MyPreferences(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enterprises)
        initViews()
        viewModel = ViewModelProviders.of(
            this,
            EnterprisesViewModelFactory(this)
        )[EnterprisesViewModel::class.java]

        if (hasInternetConnection()) {
            createEnterpriseAdapter()
            createObserver()
        }
    }

    private fun initViews() {
        setUpToolBar(findViewById(R.id.tool_bar))

        logout.setOnClickListener {
            myPreferences.clearCredentials()
            openActivityAndFinish(LoginActivity::class.java)
        }
    }

    override fun onStart() {
        super.onStart()
        NetworkEvent.register(this, Consumer {
            when (it) {
                null -> return@Consumer
                NetworkState.NO_INTERNET -> showDialog(
                    getString(R.string.connection_error),
                    getString(R.string.message_verify_connection)
                )

                NetworkState.NO_RESPONSE -> showDialog(
                    getString(R.string.unknown_error),
                    getString(R.string.message_fetch_data_not_possible)
                )

                NetworkState.UNAUTHORISED -> {
                    Toast.makeText(
                        applicationContext,
                        R.string.error_login_expired,
                        Toast.LENGTH_LONG
                    ).show()
                    openActivity(LoginActivity::class.java)
                }
            }
        })
    }

    override fun onStop() {
        super.onStop()
        NetworkEvent.unregister(this)
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

        viewModel.getProgressBar.observe(this,
            Observer {
                enterprisesProgressBar.visibility = if (it) View.VISIBLE else View.GONE
            })

        viewModel.enterprises.observe(this,
            Observer { enterprises ->
                if (enterprises == null) {
                    return@Observer
                }
                adapter.contentList = enterprises
                recyclerView.adapter = adapter
            })

        viewModel.getErrorConnection.observe(this,
            Observer {
                showDialog(
                    getString(R.string.connection_error),
                    getString(R.string.message_verify_connection)
                )
            })

        viewModel.getErrorUnauthorized.observe(this,
            Observer {
                openActivity(LoginActivity::class.java)
                finish()

            }
        )
    }

    private fun createEnterpriseAdapter() {

        adapter =
            ListingEnterprisesAdapter { itemEnterprise ->
                val bundle = Bundle()
                bundle.putInt(Constants.IntentBundle.ENTERPRISE_ID, itemEnterprise.id)
                openActivity(EnterpriseDetailActivity::class.java, bundle)
            }

        viewModel.getEnterprises(myPreferences)

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearDisposable()
    }
}
