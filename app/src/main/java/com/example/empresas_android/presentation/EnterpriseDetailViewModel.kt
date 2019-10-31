package com.example.empresas_android.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.empresas_android.data.local.MyHeaders
import com.example.empresas_android.data.service.RetrofitAnalizer
import com.example.empresas_android.data.service.model.EnterpriseByIdResponse
import com.example.empresas_android.data.service.model.EnterpriseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EnterpriseDetailViewModel: ViewModel() {
    private var enterpriseDetail: MutableLiveData<EnterpriseResponse> = MutableLiveData()

    val enterprise:LiveData<EnterpriseResponse>
        get() = enterpriseDetail

    fun getEnterpriseDetail(headers:MyHeaders, id:Int) {
        val call = RetrofitAnalizer().userService(headers).getEnterpriseById(id)

        call.enqueue(object : Callback<EnterpriseByIdResponse>{
            override fun onFailure(call: Call<EnterpriseByIdResponse>, t: Throwable) {
                Log.d("DEBUG", "on Failure: $t")
            }

            override fun onResponse(
                call: Call<EnterpriseByIdResponse>,
                response: Response<EnterpriseByIdResponse>
            ) {
                Log.d("DEBUG", "on response")
                Log.d("DEBUG", response.body().toString())
                val enterpriseR:EnterpriseResponse? = response.body()?.enterprise
                enterpriseDetail.value = enterpriseR
                Log.d("DEBUG", enterprise.value.toString())
            }

        })

    }
}