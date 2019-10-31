package com.example.empresas_android.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.empresas_android.data.local.MyHeaders
import com.example.empresas_android.data.service.RetrofitAnalizer
import com.example.empresas_android.data.service.model.EnterpriseResponse
import com.example.empresas_android.data.service.model.ListEnterprisesResponse
import com.example.empresas_android.ui.listingEnterprises.ItemEnterprise
import retrofit2.Call
import retrofit2.Response

class ListingEnterprisesViewModel : ViewModel() {

    private var itemEnterpriseList = MutableLiveData<List<EnterpriseResponse>>()
    private var itemsEnterpriseFiltered = MutableLiveData<List<EnterpriseResponse>>()

    val enterprises:LiveData<List<EnterpriseResponse>>
        get() = itemsEnterpriseFiltered


    private fun getEnterprisesFromAPI(headers: MyHeaders)  {
        val call = RetrofitAnalizer().userService(headers).getEnterprises()

        call.enqueue(object: retrofit2.Callback<ListEnterprisesResponse> {
            override fun onFailure(call: Call<ListEnterprisesResponse>, t: Throwable) {
                Log.d("DEBUG", "on Failure: $t")
            }

            override fun onResponse(
                call: Call<ListEnterprisesResponse>,
                response: Response<ListEnterprisesResponse>
            ) {
                Log.d("DEBUG", "on Response")
                val enterprises : ListEnterprisesResponse = response.body()!!
                itemEnterpriseList.value = enterprises.enterprises
                itemsEnterpriseFiltered.value = itemEnterpriseList.value
            }

        })

//        call2.subscribeOn(Schedulers.io())
//            .subscribe(object : Subscriber<List<EnterpriseResponse>>(){
//                override fun onNext(it: List<EnterpriseResponse>?) {
//                    Log.d("DEBUG", "on NEXT")
//                    Log.d("DEBUG", it.toString())
//                    if (it != null) {
//                        itemEnterpriseList.postValue(it)
//                    }
//                }
//
//                override fun onCompleted() {
//                    Log.d("DEBUG", "on completed")
//                }
//
//                override fun onError(e: Throwable?) {
//                    Log.d("DEBUG", "on Error")
//                }
//
//            })
    }

    fun getEnterprises(headers: MyHeaders) {
        getEnterprisesFromAPI(headers)
    }

    fun searchEnterprises(name: String){
        val newList : MutableList<EnterpriseResponse> = emptyList<EnterpriseResponse>().toMutableList()
        for (enterprise in itemEnterpriseList.value!!) {
            if (name in enterprise.enterprise_name){
                newList.add(enterprise)
            }
        }
        itemsEnterpriseFiltered.value = newList
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
            ItemEnterprise(
                "Empresa5",
                "Negócio",
                "Uruguai",
                "Descricao do item 5",
                "img"
            ),
            ItemEnterprise(
                "Empresa6",
                "Negócio",
                "Bolívia",
                "descricao do item 6 aqui",
                "img"
            )
        )
}