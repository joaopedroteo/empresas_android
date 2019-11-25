package com.example.empresas_android.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.empresas_android.data.service.RetrofitAnalyzer
import com.example.empresas_android.data.service.model.response.EnterpriseResponse
import com.example.empresas_android.ui.CallBackBasicViewModel
import com.example.empresas_android.ui.dagger.DaggerInfoComponent
import com.example.empresas_android.ui.dagger.Info
import com.example.empresas_android.ui.helper.applyIoScheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class EnterpriseDetailViewModel(callback:CallBackBasicViewModel) : BaseViewModel(callback) {
    private var enterpriseDetail: MutableLiveData<EnterpriseResponse> = MutableLiveData()

    private val errorConnection = MutableLiveData<Boolean>()
    private val progressBarVisible:MutableLiveData<Boolean> = MutableLiveData()

    var enterpriseName:MutableLiveData<String> = MutableLiveData()
    var enterpriseDescription:MutableLiveData<String> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var info: Info

    init {
        DaggerInfoComponent.builder().build().inject(this)
    }

    val getErrorConnection:LiveData<Boolean>
        get() = errorConnection

    val enterprise: LiveData<EnterpriseResponse>
        get() = enterpriseDetail

    val getProgressBar:LiveData<Boolean>
        get() = progressBarVisible

    private fun getEnterpriseDetailFromApi(id: Int) {
        val call = RetrofitAnalyzer().userService().getEnterpriseById(id)

        compositeDisposable.add(
            call.applyIoScheduler()
                .doOnSubscribe {
                    progressBarVisible.value = true
                }
                .doOnComplete {
                    progressBarVisible.value = false
                }
                .subscribe({
                    enterpriseDetail.value = it.enterprise
                    enterpriseName.value = it.enterprise.enterprise_name
//                    enterpriseDescription.value = it.enterprise.description

                    enterpriseDescription.value = info.text
                },{
                    progressBarVisible.value = false
                    errorConnection.value = true
                })
        )
    }

    fun getEnterpriseDetail(id: Int) {
        if(hasInternetConnection()) {
            getEnterpriseDetailFromApi(id)
        }
    }

    fun clearDisposable() {
        compositeDisposable.clear()
    }
}