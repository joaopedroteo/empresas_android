package com.example.empresas_android.viewModel

import androidx.lifecycle.ViewModel
import com.example.empresas_android.ui.helper.*

class LoginViewModel : ViewModel() {
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

    fun login(edtEmail: String, edtPassword: String) {
        emailVerifier(edtEmail)
        passwordVerifier(edtPassword)
        if (emailValid.value == true && passwordValid.value == true) {
            loginLiveData.value = true
        }
    }
}