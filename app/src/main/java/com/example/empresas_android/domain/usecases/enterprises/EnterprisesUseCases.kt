package com.example.empresas_android.domain.usecases.enterprises

import com.example.empresas_android.data.remote.model.EnterpriseByIdResponse
import com.example.empresas_android.data.remote.model.EnterpriseResponse
import com.example.empresas_android.data.remote.model.ListEnterprisesResponse
import com.example.empresas_android.domain.entities.EnterpriseEntity

interface EnterprisesUseCases{
    suspend fun getEnterprises(): List<EnterpriseEntity>

    suspend fun getEnterpriseById(id:Int): EnterpriseEntity
}