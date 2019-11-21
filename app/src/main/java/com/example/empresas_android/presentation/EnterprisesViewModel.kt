package com.example.empresas_android.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.empresas_android.data.local.preferences.MyPreferences
import com.example.empresas_android.data.service.RetrofitAnalyzer
import com.example.empresas_android.data.service.model.response.EnterpriseResponse
import com.example.empresas_android.ui.CallBackBasicViewModel
import com.example.empresas_android.ui.helper.applyIoScheduler
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class EnterprisesViewModel(callback:CallBackBasicViewModel) : BaseViewModel(callback) {

    private var itemEnterpriseList = MutableLiveData<MutableList<EnterpriseResponse>>()
    private var itemsEnterpriseFiltered = MutableLiveData<MutableList<EnterpriseResponse>>()

    private val compositeDisposable = CompositeDisposable()

    private val errorConnection = MutableLiveData<Boolean>()
    private val errorUnauthorized = MutableLiveData<Boolean>()
    private var progressBarVisible = MutableLiveData<Boolean>()


    val enterprises:LiveData<MutableList<EnterpriseResponse>>
        get() = itemsEnterpriseFiltered

    val getErrorConnection:LiveData<Boolean>
        get() = errorConnection

    val getErrorUnauthorized:LiveData<Boolean>
        get() = errorUnauthorized

    val getProgressBar:LiveData<Boolean>
        get() = progressBarVisible

    private fun getEnterprisesFromAPI(myPreferences: MyPreferences)  {
        val call = RetrofitAnalyzer().userService(myPreferences).getEnterprises()

        compositeDisposable.add(
            call.applyIoScheduler()
                .doOnSubscribe {
                    progressBarVisible.value = true
                }
                .doOnComplete {
                    progressBarVisible.value = false
                }
                .subscribe({
                    itemEnterpriseList.value = it.enterprises
                    itemsEnterpriseFiltered.value = itemEnterpriseList.value
                }, {
                    progressBarVisible.value = false
                    errorConnection.value = true
                })
        )
    }

    fun getEnterprises(myPreferences: MyPreferences) {
        getEnterprisesFromAPI(myPreferences)
    }

    fun showAllEnterprises() {
        itemsEnterpriseFiltered.value = itemEnterpriseList.value
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

    fun clearDisposable() {
        compositeDisposable.clear()
    }

}