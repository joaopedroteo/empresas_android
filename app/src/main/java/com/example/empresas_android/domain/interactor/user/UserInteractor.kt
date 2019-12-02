package com.example.empresas_android.domain.interactor.user

import com.example.empresas_android.data.Response
import com.example.empresas_android.domain.entities.UserLoginEntity

interface UserInteractor{
    suspend fun signIn(userLogin: UserLoginEntity): Response<Unit>
}