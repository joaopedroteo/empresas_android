package com.example.empresas_android.domain.useCases.enterprises

import com.example.empresas_android.data.Response
import com.example.empresas_android.data.remote.repository.UserRepository
import com.example.empresas_android.domain.entities.EnterpriseEntity

class EnterprisesUseCasesImpl(
    private val repository : UserRepository
) : EnterprisesUseCases {

    override suspend fun getEnterprises(): Response<List<EnterpriseEntity>> {
        return repository.getEnterprisesAsync()
    }

    override suspend fun getEnterpriseById(id: Int): Response<EnterpriseEntity> {
        return repository.getEnterpriseByIdAsync(id)
    }

}