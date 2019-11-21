package com.example.empresas_android.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.empresas_android.data.local.UserLogin
import com.example.empresas_android.data.local.preferences.MyPreferences
import com.example.empresas_android.data.service.RetrofitAnalyzer
import com.example.empresas_android.ui.CallBackBasicViewModel
import com.example.empresas_android.ui.helper.SingleEventLiveData
import com.example.empresas_android.ui.helper.applyIoScheduler
import com.example.empresas_android.ui.listingEnterprises.EnterprisesActivity
import io.reactivex.disposables.CompositeDisposable

class LoginViewModel(callback:CallBackBasicViewModel) : BaseViewModel(callback) {


    private val compositeDisposable = CompositeDisposable()

    private var errorEmailOrPasswordIndex = MutableLiveData<Int>()


    val errorConnection: SingleEventLiveData<Boolean> by lazy {
        SingleEventLiveData<Boolean>()
    }

    val errorMessageIndex: LiveData<Int>
        get() = errorEmailOrPasswordIndex

    private fun loginApi(myPreferences: MyPreferences, userLogin: UserLogin) {
        val call = RetrofitAnalyzer().loginService(myPreferences).signIn(userLogin)

        compositeDisposable.add(
            call.applyIoScheduler()
                .doOnComplete {
                    openActivityAndFinish(EnterprisesActivity::class.java)
                }
                .subscribe({},{
                    errorConnection.value = true
                })
        )
    }

    fun login(myPreferences: MyPreferences, edtEmail: String, edtPassword: String) {
        val userLogin = UserLogin(edtEmail, edtPassword)
        loginApi(myPreferences, userLogin)
    }

    fun clearDisposable() {
        compositeDisposable.clear()
    }

}