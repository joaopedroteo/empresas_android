package com.example.empresas_android.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.empresas_android.data.local.preferences.MyPreferences
import com.example.empresas_android.data.service.RetrofitAnalyzer
import com.example.empresas_android.data.service.model.EnterpriseByIdResponse
import com.example.empresas_android.data.service.model.EnterpriseResponse
import com.example.empresas_android.ui.helper.applyIoScheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class EnterpriseDetailViewModel : ViewModel() {
    private var enterpriseDetail: MutableLiveData<EnterpriseResponse> = MutableLiveData()

    private val errorConnection = MutableLiveData<Boolean>()
    private val progressBarVisible:MutableLiveData<Boolean> = MutableLiveData()

    var enterpriseName:MutableLiveData<String> = MutableLiveData()
    var enterpriseDescription:MutableLiveData<String> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()


    val getErrorConnection:LiveData<Boolean>
        get() = errorConnection

    val enterprise: LiveData<EnterpriseResponse>
        get() = enterpriseDetail

    val getProgressBar:LiveData<Boolean>
        get() = progressBarVisible

    fun getEnterpriseDetail(myPreferences: MyPreferences, id: Int) {
        val call = RetrofitAnalyzer().userService(myPreferences).getEnterpriseById(id)

        compositeDisposable.add(
            call.applyIoScheduler()
                .doOnSubscribe {
                    progressBarVisible.value = true
                }
                .doOnComplete {
                    progressBarVisible.value = false
                }
                .doOnError {
                    progressBarVisible.value = false
                    errorConnection.value = true
                }
                .subscribe{
                    enterpriseDetail.value = it.enterprise
                    enterpriseName.value = it.enterprise.enterprise_name
                    enterpriseDescription.value = it.enterprise.description
                }
        )

    }

    fun onDestroy() {
        compositeDisposable.clear()
    }
}