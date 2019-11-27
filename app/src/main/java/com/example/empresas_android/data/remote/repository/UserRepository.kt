package com.example.empresas_android.data.remote.repository

import com.example.empresas_android.domain.entities.UserLoginEntity
import com.example.empresas_android.data.remote.model.EnterpriseByIdResponse
import com.example.empresas_android.data.remote.model.ListEnterprisesResponse
import com.example.empresas_android.data.remote.model.LoginResponse
import com.example.empresas_android.domain.entities.EnterpriseEntity
import kotlinx.coroutines.Deferred

interface UserRepository {
    suspend fun signInAsync(userLogin: UserLoginEntity): Deferred<LoginResponse>

    suspend fun getEnterprisesAsync(): Deferred<List<EnterpriseEntity>>

    suspend fun getEnterpriseByIdAsync(id:Int): Deferred<EnterpriseEntity>
}