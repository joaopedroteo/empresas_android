package com.example.empresas_android.module

import com.example.empresas_android.presentation.EnterpriseDetailViewModel
import com.example.empresas_android.presentation.EnterprisesViewModel
import com.example.empresas_android.presentation.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { LoginViewModel(get(), get()) }

    viewModel { EnterprisesViewModel(get(), get()) }

    viewModel { EnterpriseDetailViewModel(get(), get()) }

}