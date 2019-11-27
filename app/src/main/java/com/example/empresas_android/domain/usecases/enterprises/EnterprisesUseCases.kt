package com.example.empresas_android.domain.usecases.enterprises

import com.example.empresas_android.data.remote.model.EnterpriseByIdResponse
import com.example.empresas_android.data.remote.model.EnterpriseResponse
import com.example.empresas_android.data.remote.model.ListEnterprisesResponse

interface EnterprisesUseCases{
    suspend fun getEnterprises(): ListEnterprisesResponse

    suspend fun getEnterpriseById(id:Int): EnterpriseByIdResponse
}