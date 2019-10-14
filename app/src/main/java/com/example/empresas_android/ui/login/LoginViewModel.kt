package com.example.empresas_android.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.empresas_android.ui.helper.SingleEventLiveData

class LoginViewModel : ViewModel() {
    val loginLiveData: SingleEventLiveData<Boolean> by lazy {
        SingleEventLiveData<Boolean>()
    }

    val errorLoginLiveData: SingleEventLiveData<Boolean> by lazy {
        SingleEventLiveData<Boolean>()
    }

    private fun validateUser(edtEmail: String, edtPassword: String) =
        edtEmail.isNotEmpty() && edtPassword.isNotEmpty()

    fun login(edtEmail: String, edtPassword: String) {
        validateUser(edtEmail, edtPassword).takeIf { it }?.run {
            loginLiveData.value = true
        } ?: run { errorLoginLiveData.value = true }
    }
}