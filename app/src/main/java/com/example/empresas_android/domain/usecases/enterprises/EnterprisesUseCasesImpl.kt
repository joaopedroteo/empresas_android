package com.example.empresas_android.domain.usecases.enterprises

import com.example.empresas_android.data.remote.model.EnterpriseByIdResponse
import com.example.empresas_android.data.remote.model.ListEnterprisesResponse
import com.example.empresas_android.data.remote.repository.UserRepositoryImpl

class EnterprisesUseCasesImpl : EnterprisesUseCases {
    private val repository = UserRepositoryImpl()

    override suspend fun getEnterprises(): ListEnterprisesResponse {
        return repository.getEnterprisesAsync().await()
    }

    override suspend fun getEnterpriseById(id: Int): EnterpriseByIdResponse {
        return repository.getEnterpriseByIdAsync(id).await()
    }

}