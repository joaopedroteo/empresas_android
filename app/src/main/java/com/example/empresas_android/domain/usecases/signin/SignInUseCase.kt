package com.example.empresas_android.domain.usecases.signin

import com.example.empresas_android.domain.entities.UserLoginEntity
import com.example.empresas_android.data.remote.model.LoginResponse

interface SignInUseCase{
    suspend fun signIn(userLogin: UserLoginEntity): LoginResponse
}