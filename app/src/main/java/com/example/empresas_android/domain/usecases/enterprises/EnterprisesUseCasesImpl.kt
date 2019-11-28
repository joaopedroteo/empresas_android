package com.example.empresas_android.domain.usecases.enterprises

import com.example.empresas_android.data.remote.repository.UserRepositoryImpl
import com.example.empresas_android.domain.entities.EnterpriseEntity

class EnterprisesUseCasesImpl : EnterprisesUseCases {
    private val repository = UserRepositoryImpl()

    override suspend fun getEnterprises(): List<EnterpriseEntity> {
        return repository.getEnterprisesAsync().await()
    }

    override suspend fun getEnterpriseById(id: Int): EnterpriseEntity {
        return repository.getEnterpriseByIdAsync(id).await()
    }

}