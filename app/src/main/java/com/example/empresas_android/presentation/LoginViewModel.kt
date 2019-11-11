package com.example.empresas_android.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.empresas_android.R
import com.example.empresas_android.data.local.MyHeaders
import com.example.empresas_android.data.local.UserLogin
import com.example.empresas_android.data.local.preferences.MyPreferences
import com.example.empresas_android.data.local.preferences.PreferencesRepository
import com.example.empresas_android.data.service.HttpCodes
import com.example.empresas_android.data.service.RetrofitAnalizer
import com.example.empresas_android.data.service.model.LoginResponse
import com.example.empresas_android.ui.helper.SingleEventLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val tag = "DEBUG"

    private val compositeDisposable = CompositeDisposable()

    private var errorEmailOrPasswordIndex = MutableLiveData<Int>()

    val loginLiveData: SingleEventLiveData<Boolean> by lazy {
        SingleEventLiveData<Boolean>()
    }

    val errorConnection: SingleEventLiveData<Boolean> by lazy {
        SingleEventLiveData<Boolean>()
    }

    val errorMessageIndex:LiveData<Int>
        get() = errorEmailOrPasswordIndex

    private fun loginApi(myPreferences: MyPreferences, userLogin: UserLogin) {
        val call = RetrofitAnalizer().loginService(myPreferences).signIn(userLogin)


        compositeDisposable.add(
            call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<LoginResponse>() {

                    override fun onComplete() {
                        Log.d(tag, "onClomplete")
                        loginLiveData.value = true
                    }

                    override fun onNext(t: LoginResponse) {
                        Log.d(tag, t.toString())
                    }

                    override fun onError(e: Throwable) {
                        errorConnection.value = true
                    }

                })
        )
    }


//    private fun loginApi(userLogin: UserLogin) {
//        val call = RetrofitAnalizer().loginService().signIn(userLogin)
//        call.enqueue(object : Callback<LoginResponse> {
//            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//
//            }
//
//            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//                if (response.code() == HttpCodes.OK.value) {
//                    val accessToken = response.headers()["access-token"]
//                    val client = response.headers()["client"]
//                    val uid = response.headers()["uid"]
//
//                    if (accessToken != null && client != null && uid != null) {
//                        headers = MyHeaders(accessToken, client, uid)
//                        loginLiveData.value = true
//                    }
//                }
//                if(response.code() == HttpCodes.UNAUTHORIZED.value) {
//                    errorEmailOrPasswordIndex.postValue(R.string.error_email_or_password_invalid)
//                }
//            }
//        })
//    }

    fun login(myPreferences:MyPreferences, edtEmail: String, edtPassword: String) {
        val userLogin = UserLogin(edtEmail, edtPassword)
        loginApi(myPreferences, userLogin)
    }

    fun destroy() {
        compositeDisposable.clear()
    }

}