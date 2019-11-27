package com.example.empresas_android.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.empresas_android.domain.entities.UserLoginEntity
import com.example.empresas_android.domain.usecases.signin.SignInUseCaseImpl
import com.example.empresas_android.ui.helper.SingleEventLiveData
import kotlinx.coroutines.async

class LoginViewModel : CoroutineViewModel() {


    private var errorEmailOrPasswordIndex = MutableLiveData<Int>()


    val loginLiveData: SingleEventLiveData<Boolean> by lazy {
        SingleEventLiveData<Boolean>()
    }

    val errorConnection: SingleEventLiveData<Boolean> by lazy {
        SingleEventLiveData<Boolean>()
    }

    val errorMessageIndex:LiveData<Int>
        get() = errorEmailOrPasswordIndex

    private suspend fun loginApi(userLogin: UserLoginEntity) {

        jobs add async {
            try {
                SignInUseCaseImpl().signIn(userLogin)
                loginLiveData.value = true
            } catch (e:Error) {
                errorConnection.value = true
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

    suspend fun login(edtEmail: String, edtPassword: String) {
        val userLogin = UserLoginEntity(
            edtEmail,
            edtPassword
        )
        loginApi(userLogin)
    }

}