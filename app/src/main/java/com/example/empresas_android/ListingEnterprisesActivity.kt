package com.example.empresas_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_listing_enterprises.*

class ListingEnterprisesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing_enterprises)
        setTitle(R.string.title_listing_screen)
        var list = listOf<ItemEnterprise>(
            ItemEnterprise("Teste1","des1", "img1"),
            ItemEnterprise("Teste2","des2", "img1"),
            ItemEnterprise("Teste3","des3", "img1"),
            ItemEnterprise("Teste4","des4", "img1"),
            ItemEnterprise("Teste5","des5", "img1"),
            ItemEnterprise("Teste6","des6", "img1")
        )

        val adapter = ListingEnterprisesAdapter {
                itemEnterprise ->
            val intent = Intent(
                this@ListingEnterprisesActivity,
                EnterpriseDetailActivity::class.java)
            intent.putExtra("arg_enterprise_name", itemEnterprise.name)
            intent.putExtra("arg_enterprise_description", itemEnterprise.description)
            intent.putExtra("arg_enterprise_image", itemEnterprise.image)
            startActivity(intent)
        }
        adapter.contentList = list
        recyclerView.adapter = adapter
    }
}
