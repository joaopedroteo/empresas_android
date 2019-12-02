package com.example.empresas_android.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.empresas_android.data.Response
import com.example.empresas_android.domain.entities.EnterpriseEntity
import com.example.empresas_android.domain.interactor.enterprises.EnterprisesInteractor
import com.example.empresas_android.utils.ThreadContextProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EnterpriseDetailViewModel(
    private val enterprisesInteractor: EnterprisesInteractor,
    private val contextProvider: ThreadContextProvider

) : ViewModel() {
    private var enterpriseDetail: MutableLiveData<EnterpriseEntity> = MutableLiveData()

    private val errorConnection = MutableLiveData<Boolean>()

    val getErrorConnection: LiveData<Boolean>
        get() = errorConnection

    val enterprise: LiveData<EnterpriseEntity>
        get() = enterpriseDetail


    suspend fun getEnterpriseDetail(id: Int) {

        viewModelScope.launch(contextProvider.io) {
            val response = enterprisesInteractor.getEnterpriseById(id)
            withContext(contextProvider.ui) {
                when (response) {
                    is Response.Success -> {
                        enterpriseDetail.postValue(response.data)
                    }
                    is Response.Failure -> {
                        errorConnection.postValue(true)
                    }
                }
            }
        }

    }


//    fun getEnterpriseDetail(id:Int) {
//        val call = RetrofitAnalizer()
//            .userService().getEnterpriseByIdAsync(id)
//
//        call.enqueue(object : Callback<EnterpriseByIdResponse>{
//            override fun onFailure(call: Call<EnterpriseByIdResponse>, t: Throwable) {
//                errorConnection.value = true
//            }
//
//            override fun onResponse(
//                call: Call<EnterpriseByIdResponse>,
//                response: Response<EnterpriseByIdResponse>
//            ) {
//                if(response.code() == HttpCodes.OK.value){
//                    val enterpriseR:EnterpriseResponse? = response.body()?.enterprise
//                    enterpriseDetail.value = enterpriseR
//                }
//            }
//
//        })
//
//    }
}