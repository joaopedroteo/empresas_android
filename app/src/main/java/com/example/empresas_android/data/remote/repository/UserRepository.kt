package com.example.empresas_android.data.remote.repository

import com.example.empresas_android.data.Response
import com.example.empresas_android.data.remote.model.request.UserLoginRequest
import com.example.empresas_android.domain.entities.EnterpriseEntity

interface UserRepository {
    suspend fun signInAsync(userLogin: UserLoginRequest): Response<Unit>

    suspend fun getEnterprisesAsync(): Response<List<EnterpriseEntity>>

    suspend fun getEnterpriseByIdAsync(id: Int): Response<EnterpriseEntity>
}