package com.example.empresas_android.domain.useCases.enterprises

import com.example.empresas_android.data.Response
import com.example.empresas_android.domain.entities.EnterpriseEntity

interface EnterprisesUseCases{
    suspend fun getEnterprises(): Response<List<EnterpriseEntity>>

    suspend fun getEnterpriseById(id:Int): Response<EnterpriseEntity>
}