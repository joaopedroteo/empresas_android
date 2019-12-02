package com.example.empresas_android.presentation

import androidx.lifecycle.*
import com.example.empresas_android.data.Response
import com.example.empresas_android.domain.entities.EnterpriseEntity
import com.example.empresas_android.domain.interactor.enterprises.EnterprisesInteractor
import com.example.empresas_android.utils.ThreadContextProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class EnterprisesViewModel(
    private val enterprisesInteractor: EnterprisesInteractor,
    private val contextProvider: ThreadContextProvider
) : ViewModel(), LifecycleObserver {

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


    private fun getEnterprisesFromAPI() {

        viewModelScope.launch(contextProvider.io) {
            val response = enterprisesInteractor.getEnterprises()
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

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun getEnterprises() {
        getEnterprisesFromAPI()
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