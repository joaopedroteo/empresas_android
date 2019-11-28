package com.example.empresas_android.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.empresas_android.presentation.LoginViewModel
import com.example.empresas_android.ui.CallBackBasicViewModel

class LoginViewModelFactory(
    private val callBackBasicViewModel: CallBackBasicViewModel
): ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel> create(modelClass:Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return LoginViewModel(callBackBasicViewModel) as T
    }
}