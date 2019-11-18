package com.example.empresas_android.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.empresas_android.data.service.model.EnterpriseResponse

class ItemEnterpriseViewModel() : ViewModel() {

    var enterpriseName: MutableLiveData<String> = MutableLiveData()
    var enterpriseCity: MutableLiveData<String> = MutableLiveData()
    var enterpriseCountry: MutableLiveData<String> = MutableLiveData()

    constructor(enterpriseResponse: EnterpriseResponse) : this() {
        enterpriseName.value = enterpriseResponse.enterprise_name
        enterpriseCity.value = enterpriseResponse.city
        enterpriseCountry.value = enterpriseResponse.country
    }

}