package com.example.empresas_android.presentation

import androidx.lifecycle.*
import com.example.empresas_android.data.Response
import com.example.empresas_android.domain.entities.UserLoginEntity
import com.example.empresas_android.domain.interactor.user.UserInteractor
import com.example.empresas_android.ui.helper.SingleEventLiveData
import com.example.empresas_android.utils.ThreadContextProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val userInteractor : UserInteractor,
    private val contextProvider: ThreadContextProvider
): ViewModel(), LifecycleObserver {

    private var errorEmailOrPasswordIndex = MutableLiveData<Int>()

    private var messageError = ""

    fun getMessageError() = messageError

//    private val contextProvider = ThreadContextProvider()

    val loginLiveData: SingleEventLiveData<Boolean> by lazy {
        SingleEventLiveData<Boolean>()
    }

    val errorConnection: SingleEventLiveData<Boolean> by lazy {
        SingleEventLiveData<Boolean>()
    }

    val errorMessageIndex: LiveData<Int>
        get() = errorEmailOrPasswordIndex

    fun loginApi(userLogin: UserLoginEntity) {
        viewModelScope.launch(contextProvider.io) {
            val response = userInteractor.signIn(userLogin)
            withContext(contextProvider.ui) {
                when (response) {
                    is Response.Success -> {
                        loginLiveData.postValue(true)
                    }
                    is Response.Failure -> {
                        messageError = response.throwable.message ?: ""
                        errorConnection.postValue(true)
                    }
                }
            }
        }
    }

//    private fun loginApi(userLogin: UserLogin) {
//        val call = RetrofitAnalizer()
//            .userService().signInAsync(userLogin)
//        call.enqueue(object : Callback<LoginResponse> {
//            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                errorConnection.value = true
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

    fun login(edtEmail: String, edtPassword: String) {
        val userLogin = UserLoginEntity(
            edtEmail,
            edtPassword
        )
        loginApi(userLogin)
    }

}