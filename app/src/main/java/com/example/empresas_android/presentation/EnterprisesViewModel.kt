package com.example.empresas_android.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.empresas_android.data.local.MyHeaders
import com.example.empresas_android.data.local.preferences.MyPreferences
import com.example.empresas_android.data.local.preferences.PreferencesRepository
import com.example.empresas_android.data.service.HttpCodes
import com.example.empresas_android.data.service.RetrofitAnalizer
import com.example.empresas_android.data.service.model.EnterpriseResponse
import com.example.empresas_android.data.service.model.ListEnterprisesResponse
import retrofit2.Call
import retrofit2.Response
import java.util.*

class EnterprisesViewModel : ViewModel() {

    private var itemEnterpriseList = MutableLiveData<List<EnterpriseResponse>>()
    private var itemsEnterpriseFiltered = MutableLiveData<List<EnterpriseResponse>>()

    private val errorConnection = MutableLiveData<Boolean>()
    private val errorUnauthorized = MutableLiveData<Boolean>()


    val enterprises:LiveData<List<EnterpriseResponse>>
        get() = itemsEnterpriseFiltered

    val getErrorConnection:LiveData<Boolean>
        get() = errorConnection

    val getErrorUnauthorized:LiveData<Boolean>
        get() = errorUnauthorized


    private fun getEnterprisesFromAPI(myPreferences: MyPreferences)  {
        val call = RetrofitAnalizer().userService(myPreferences).getEnterprises()

        call.enqueue(object: retrofit2.Callback<ListEnterprisesResponse> {
            override fun onFailure(call: Call<ListEnterprisesResponse>, t: Throwable) {
                errorConnection.value = true
            }

            override fun onResponse(
                call: Call<ListEnterprisesResponse>,
                response: Response<ListEnterprisesResponse>
            ) {

                if (response.code() == HttpCodes.UNAUTHORIZED.value) {
                    errorUnauthorized.value = true
                } else {
                    val enterprises : ListEnterprisesResponse = response.body()!!
                    itemEnterpriseList.value = enterprises.enterprises
                    itemsEnterpriseFiltered.value = itemEnterpriseList.value
                }
            }

        })
    }

    fun getEnterprises(myPreferences: MyPreferences) {
        getEnterprisesFromAPI(myPreferences)
    }

    fun searchEnterprises(name: String){
        val newList : MutableList<EnterpriseResponse> = emptyList<EnterpriseResponse>().toMutableList()

        for (enterprise in itemEnterpriseList.value.orEmpty()) {
            if (name.toLowerCase(Locale.US) in enterprise.enterprise_name.toLowerCase(Locale.US)) {
                newList.add(enterprise)
            }
        }

        itemsEnterpriseFiltered.value = newList
    }

}