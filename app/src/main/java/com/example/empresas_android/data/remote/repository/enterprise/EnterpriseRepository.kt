package com.example.empresas_android.data.remote.repository.enterprise

import com.example.empresas_android.data.Response
import com.example.empresas_android.domain.entities.EnterpriseEntity

interface EnterpriseRepository {
    suspend fun getEnterprisesAsync(): Response<List<EnterpriseEntity>>

    suspend fun getEnterpriseByIdAsync(id: Int): Response<EnterpriseEntity>
}