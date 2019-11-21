package com.example.empresas_android.presentation.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.empresas_android.presentation.EnterprisesViewModel
import com.example.empresas_android.ui.CallBackBasicViewModel

class EnterprisesViewModelFactory(
    private val callBackBasicViewModel: CallBackBasicViewModel
): ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel> create(modelClass:Class<T>): T {
        return EnterprisesViewModel(callBackBasicViewModel) as T
    }
}