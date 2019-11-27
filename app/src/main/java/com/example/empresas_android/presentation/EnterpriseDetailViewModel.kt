package com.example.empresas_android.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.empresas_android.domain.entities.EnterpriseEntity
import com.example.empresas_android.domain.usecases.enterprises.EnterprisesUseCasesImpl
import kotlinx.coroutines.async

class EnterpriseDetailViewModel: CoroutineViewModel() {
    private var enterpriseDetail: MutableLiveData<EnterpriseEntity> = MutableLiveData()

    private val errorConnection = MutableLiveData<Boolean>()

    val getErrorConnection:LiveData<Boolean>
        get() = errorConnection

    val enterprise:LiveData<EnterpriseEntity>
        get() = enterpriseDetail


    suspend fun getEnterpriseDetail(id:Int) {
        jobs add async {
            try {
                val enterprise = EnterprisesUseCasesImpl().getEnterpriseById(id)
                enterpriseDetail.value = enterprise
            } catch (e:Error) {
                errorConnection.value = true
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