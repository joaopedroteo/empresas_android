package com.example.empresas_android.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.empresas_android.ItemEnterprise

class ListingEnterprisesViewModel : ViewModel() {

    var itemEnterpriseList = MutableLiveData<List<ItemEnterprise>>()


    fun getEnterprises(): LiveData<List<ItemEnterprise>> {
        itemEnterpriseList.value = createEnterpriseList()
        return itemEnterpriseList
    }


    private fun createEnterpriseList(): List<ItemEnterprise> =
        listOf(
            ItemEnterprise(
                "Empresa1",
                "Negócio",
                "Brasil",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "img"
            ),
            ItemEnterprise(
                "Empresa2",
                "Negócio",
                "Brasil",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "img"
            ),
            ItemEnterprise(
                "Empresa3",
                "Negócio",
                "Argentina",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "img"
            ),
            ItemEnterprise(
                "Empresa4",
                "Negócio",
                "Brasil",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "img"
            ),
            ItemEnterprise("Empresa5", "Negócio", "Uruguai", "Descricao do item 5", "img"),
            ItemEnterprise("Empresa6", "Negócio", "Bolívia", "descricao do item 6 aqui", "img")
        )
}