package com.example.empresas_android.data.remote.repository.user

import com.example.empresas_android.data.Response
import com.example.empresas_android.data.remote.model.request.UserLoginRequest

interface UserRepository {
    suspend fun signInAsync(userLogin: UserLoginRequest): Response<Unit>

}