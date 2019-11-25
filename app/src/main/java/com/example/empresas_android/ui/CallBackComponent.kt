package com.example.empresas_android.ui

import com.example.empresas_android.presentation.LoginViewModel
import dagger.Component

@Component
interface CallBackComponent {

    fun inject(loginViewModel: LoginViewModel)

}