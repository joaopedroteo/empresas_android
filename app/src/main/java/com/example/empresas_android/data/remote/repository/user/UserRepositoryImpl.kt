package com.example.empresas_android.data.remote.repository.user

import com.example.empresas_android.data.Response
import com.example.empresas_android.data.remote.model.request.UserLoginRequest
import com.example.empresas_android.data.remote.repository.user.UserRepository
import com.example.empresas_android.data.remote.service.WebService
import com.example.empresas_android.extentions.apiCall

class UserRepositoryImpl(
    private val webService: WebService
) : UserRepository {

    override suspend fun signInAsync(userLogin: UserLoginRequest): Response<Unit> =
        apiCall {
            webService.signInAsync(userLogin)
        }
}