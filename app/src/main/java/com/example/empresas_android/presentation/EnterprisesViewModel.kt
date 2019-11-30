package com.example.empresas_android.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.empresas_android.data.Response
import com.example.empresas_android.domain.entities.EnterpriseEntity
import com.example.empresas_android.domain.useCases.enterprises.EnterprisesUseCases
import com.example.empresas_android.utils.ThreadContextProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class EnterprisesViewModel(
    private val enterprisesUseCases: EnterprisesUseCases,
    private val contextProvider: ThreadContextProvider
) : ViewModel() {

    private var itemEnterpriseList = MutableLiveData<List<EnterpriseEntity>>()
    private var itemsEnterpriseFiltered = MutableLiveData<List<EnterpriseEntity>>()

    private val errorConnection = MutableLiveData<Boolean>()
    private val errorUnauthorized = MutableLiveData<Boolean>()

    val enterprises: LiveData<List<EnterpriseEntity>>
        get() = itemsEnterpriseFiltered

    val getErrorConnection: LiveData<Boolean>
        get() = errorConnection

    val getErrorUnauthorized: LiveData<Boolean>
        get() = errorUnauthorized


    private suspend fun getEnterprisesFromAPI() {

        viewModelScope.launch(contextProvider.io) {
            val response = enterprisesUseCases.getEnterprises()
            withContext(contextProvider.ui) {
                when (response) {
                    is Response.Success -> {
                        itemEnterpriseList.postValue(response.data)
                        itemsEnterpriseFiltered.postValue(response.data)

                    }
                    is Response.Failure -> {
                        errorConnection.postValue(true)
                    }
                }
            }
        }
    }

//    private fun getEnterprisesFromAPI()  {
//        val call = RetrofitAnalizer()
//            .userService().getEnterprisesAsync()
//
//        call.enqueue(object: retrofit2.Callback<ListEnterprisesResponse> {
//            override fun onFailure(call: Call<ListEnterprisesResponse>, t: Throwable) {
//                errorConnection.value = true
//            }
//
//            override fun onResponse(
//                call: Call<ListEnterprisesResponse>,
//                response: Response<ListEnterprisesResponse>
//            ) {
//
//                if (response.code() == HttpCodes.UNAUTHORIZED.value) {
//                    errorUnauthorized.value = true
//                } else {
//                    val enterprises : ListEnterprisesResponse = response.body()!!
//                    itemEnterpriseList.value = enterprises.enterprises
//                    itemsEnterpriseFiltered.value = itemEnterpriseList.value
//                }
//            }
//
//        })
//    }

    suspend fun getEnterprises() {
        getEnterprisesFromAPI()
    }

    fun searchEnterprises(name: String) {
        val newList: MutableList<EnterpriseEntity> = emptyList<EnterpriseEntity>().toMutableList()

        for (enterprise in itemEnterpriseList.value.orEmpty()) {
            if (name.toLowerCase(Locale.US) in enterprise.enterprise_name.toLowerCase(Locale.US)) {
                newList.add(enterprise)
            }
        }

        itemsEnterpriseFiltered.value = newList
    }

}