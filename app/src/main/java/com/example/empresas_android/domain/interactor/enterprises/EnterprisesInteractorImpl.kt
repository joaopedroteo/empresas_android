package com.example.empresas_android.domain.interactor.enterprises

import com.example.empresas_android.data.Response
import com.example.empresas_android.data.remote.repository.enterprise.EnterpriseRepository
import com.example.empresas_android.domain.entities.EnterpriseEntity

class EnterprisesInteractorImpl(
    private val repository : EnterpriseRepository
) : EnterprisesInteractor {

    override suspend fun getEnterprises(): Response<List<EnterpriseEntity>> {
        return repository.getEnterprisesAsync()
    }

    override suspend fun getEnterpriseById(id: Int): Response<EnterpriseEntity> {
        return repository.getEnterpriseByIdAsync(id)
    }

}