package com.example.empresas_android.presentation

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.empresas_android.R
import com.example.empresas_android.data.local.MyHeaders
import com.example.empresas_android.data.local.UserLogin
import com.example.empresas_android.data.service.RetrofitAnalizer
import com.example.empresas_android.data.service.model.LoginResponse
import com.example.empresas_android.extentions.*
import com.example.empresas_android.ui.helper.SingleEventLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private lateinit var headers:MyHeaders


    val loginLiveData: SingleEventLiveData<Boolean> by lazy {
        SingleEventLiveData<Boolean>()
    }


    val errorEmailInvalid: SingleEventLiveData<Boolean> by lazy {
        SingleEventLiveData<Boolean>()
    }

    val emailValid: SingleEventLiveData<Boolean> by lazy {
        SingleEventLiveData<Boolean>()
    }

    val errorHasNotNumberPass: SingleEventLiveData<Boolean> by lazy {
        SingleEventLiveData<Boolean>()
    }

    val errorHasNotUpperCase: SingleEventLiveData<Boolean> by lazy {
        SingleEventLiveData<Boolean>()
    }

    val errorHasNotLowerCase: SingleEventLiveData<Boolean> by lazy {
        SingleEventLiveData<Boolean>()
    }

    val errorHasNotEspecialCharacters: SingleEventLiveData<Boolean> by lazy {
        SingleEventLiveData<Boolean>()
    }

    val errorHasNotMinimumSize: SingleEventLiveData<Boolean> by lazy {
        SingleEventLiveData<Boolean>()
    }

    val passwordValid: SingleEventLiveData<Boolean> by lazy {
        SingleEventLiveData<Boolean>()
    }

    val errorEmptyEmailField: SingleEventLiveData<Boolean> by lazy {
        SingleEventLiveData<Boolean>()
    }

    val errorEmptyPasswordField: SingleEventLiveData<Boolean> by lazy {
        SingleEventLiveData<Boolean>()
    }

    private fun emailVerifier(edtEmail: String) {
        when{
            edtEmail.isEmpty() -> errorEmptyEmailField.value = true
            !edtEmail.isEmail() -> errorEmailInvalid.value = true
            else -> emailValid.value = true
        }
    }

    private fun passwordVerifier(edtPassword: String){
        when {
            edtPassword.isEmpty() -> errorEmptyPasswordField.value = true
            !edtPassword.hasNumber() -> errorHasNotNumberPass.value = true
            !edtPassword.hasUpperCase() -> errorHasNotUpperCase.value = true
            !edtPassword.hasLowerCase() -> errorHasNotLowerCase.value = true
            !edtPassword.hasEspecialCharacters() -> errorHasNotEspecialCharacters.value = true
            !edtPassword.hasSixCharacter() -> errorHasNotMinimumSize.value = true
            else -> passwordValid.value = true
        }
    }


    private fun loginApi(userLogin: UserLogin) {
        val call = RetrofitAnalizer().loginService().signIn(userLogin)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("DEUBUG", "on Failure")
                Log.d("DEBUG", t.toString())
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.d("DEBUG", "deu bom")
                Log.d("DEBUG", response.toString())
                if (response.code() == 200) {
                    val accessToken = response.headers()["access-token"]
                    val client = response.headers()["client"]
                    val uid = response.headers()["uid"]
                    Log.d("DEBUG", "at: $accessToken, c: $client, uid: $uid")
                    if (accessToken != null && client != null && uid != null) {
                        headers = MyHeaders(accessToken, client, uid)
                        loginLiveData.value = true
                    }
                }
            }
        })
    }

    fun login(edtEmail: String, edtPassword: String) {
        val userLogin = UserLogin(edtEmail, edtPassword)
        loginApi(userLogin)
        if (emailValid.value == true && passwordValid.value == true) {
            loginLiveData.value = true
        }
    }

    fun getHeaders(): MyHeaders {
        return headers
    }
}