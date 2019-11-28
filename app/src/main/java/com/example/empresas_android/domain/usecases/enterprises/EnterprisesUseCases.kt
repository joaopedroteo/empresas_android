package com.example.empresas_android.domain.usecases.enterprises

import com.example.empresas_android.domain.entities.EnterpriseEntity

interface EnterprisesUseCases{
    suspend fun getEnterprises(): List<EnterpriseEntity>

    suspend fun getEnterpriseById(id:Int): EnterpriseEntity
}