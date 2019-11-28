package com.example.empresas_android.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.empresas_android.domain.entities.EnterpriseEntity
import com.example.empresas_android.domain.usecases.enterprises.EnterprisesUseCasesImpl
import kotlinx.coroutines.async
import java.util.*

class EnterprisesViewModel : CoroutineViewModel() {

    private var itemEnterpriseList = MutableLiveData<List<EnterpriseEntity>>()
    private var itemsEnterpriseFiltered = MutableLiveData<List<EnterpriseEntity>>()

    private val errorConnection = MutableLiveData<Boolean>()
    private val errorUnauthorized = MutableLiveData<Boolean>()

    val enterprises:LiveData<List<EnterpriseEntity>>
        get() = itemsEnterpriseFiltered

    val getErrorConnection:LiveData<Boolean>
        get() = errorConnection

    val getErrorUnauthorized:LiveData<Boolean>
        get() = errorUnauthorized


    private suspend fun getEnterprisesFromAPI() {
        jobs add async {
            try {
                val enterprises = EnterprisesUseCasesImpl().getEnterprises()
                itemEnterpriseList.value = enterprises
                itemsEnterpriseFiltered.value = itemEnterpriseList.value
            } catch(e:Error) {
                errorConnection.value = true
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

    fun searchEnterprises(name: String){
        val newList : MutableList<EnterpriseEntity> = emptyList<EnterpriseEntity>().toMutableList()

        for (enterprise in itemEnterpriseList.value.orEmpty()) {
            if (name.toLowerCase(Locale.US) in enterprise.enterprise_name.toLowerCase(Locale.US)) {
                newList.add(enterprise)
            }
        }

        itemsEnterpriseFiltered.value = newList
    }

}