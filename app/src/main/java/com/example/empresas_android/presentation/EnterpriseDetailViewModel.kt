package com.example.empresas_android.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.empresas_android.data.local.MyHeaders
import com.example.empresas_android.data.service.HttpCodes
import com.example.empresas_android.data.service.RetrofitAnalizer
import com.example.empresas_android.data.service.model.EnterpriseByIdResponse
import com.example.empresas_android.data.service.model.EnterpriseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EnterpriseDetailViewModel: ViewModel() {
    //imports nao sendo usados
    private var enterpriseDetail: MutableLiveData<EnterpriseResponse> = MutableLiveData()

    private val errorConnection = MutableLiveData<Boolean>()

    val getErrorConnection:LiveData<Boolean>
        get() = errorConnection

    val enterprise:LiveData<EnterpriseResponse>
        get() = enterpriseDetail

    fun getEnterpriseDetail(headers:MyHeaders, id:Int) {
        val call = RetrofitAnalizer().userService(headers).getEnterpriseById(id)

        call.enqueue(object : Callback<EnterpriseByIdResponse>{
            override fun onFailure(call: Call<EnterpriseByIdResponse>, t: Throwable) {
                errorConnection.value = true
            }

            override fun onResponse(
                call: Call<EnterpriseByIdResponse>,
                response: Response<EnterpriseByIdResponse>
            ) {
                if(response.code() == HttpCodes.OK.value){
                    val enterpriseR:EnterpriseResponse? = response.body()?.enterprise
                    enterpriseDetail.value = enterpriseR
                }
            }

        })

    }
}