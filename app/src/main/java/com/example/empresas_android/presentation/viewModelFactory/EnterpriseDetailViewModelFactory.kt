package com.example.empresas_android.presentation.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.empresas_android.presentation.EnterpriseDetailViewModel
import com.example.empresas_android.ui.CallBackBasicViewModel

class EnterpriseDetailViewModelFactory (
    private val callBackBasicViewModel: CallBackBasicViewModel
): ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel> create(modelClass:Class<T>): T {
        return EnterpriseDetailViewModel(callBackBasicViewModel) as T
    }
}
