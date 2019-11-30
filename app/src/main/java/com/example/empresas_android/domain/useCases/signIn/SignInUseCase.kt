package com.example.empresas_android.domain.useCases.signIn

import com.example.empresas_android.data.Response
import com.example.empresas_android.domain.entities.UserLoginEntity
import com.example.empresas_android.data.remote.model.response.LoginResponse

interface SignInUseCase{
    suspend fun signIn(userLogin: UserLoginEntity): Response<LoginResponse>
}