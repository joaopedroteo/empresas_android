package com.example.empresas_android

import android.content.Intent
import android.graphics.Color
import android.graphics.ColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.SearchView
import androidx.constraintlayout.solver.widgets.WidgetContainer
import kotlinx.android.synthetic.main.activity_listing_enterprises.*
import kotlinx.android.synthetic.main.custom_edit_text.*
import org.w3c.dom.Text

class ListingEnterprisesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing_enterprises)
        setTitle(R.string.title_listing_screen)
        setSupportActionBar(findViewById(R.id.tool_bar))

        var searchView = findViewById<androidx.appcompat.widget.SearchView>(R.id.search_view)

        var icon = searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_button)
        icon.setColorFilter(Color.WHITE)

        var editText = searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        editText.setTextColor(Color.WHITE)


        var list = listOf<ItemEnterprise>(
            ItemEnterprise("Empresa1","Negócio", "Brasil", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","img"),
            ItemEnterprise("Empresa2","Negócio", "Brasil", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "img"),
            ItemEnterprise("Empresa3","Negócio", "Argentina", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "img"),
            ItemEnterprise("Empresa4","Negócio", "Brasil", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "img"),
            ItemEnterprise("Empresa5","Negócio", "Uruguai", "Descricao do item 5", "img"),
            ItemEnterprise("Empresa6","Negócio", "Bolívia", "descricao do item 6 aqui", "img")
        )

        val adapter = ListingEnterprisesAdapter {
                itemEnterprise ->
            val intent = Intent(
                this@ListingEnterprisesActivity,
                EnterpriseDetailActivity::class.java)
            intent.putExtra("arg_enterprise_name", itemEnterprise.name)
            intent.putExtra("arg_enterprise_area", itemEnterprise.area)
            intent.putExtra("arg_enterprise_country", itemEnterprise.country)
            intent.putExtra("arg_enterprise_description", itemEnterprise.description)
            intent.putExtra("arg_enterprise_image", itemEnterprise.image)
            startActivity(intent)
        }
        adapter.contentList = list
        recyclerView.adapter = adapter
    }
}
