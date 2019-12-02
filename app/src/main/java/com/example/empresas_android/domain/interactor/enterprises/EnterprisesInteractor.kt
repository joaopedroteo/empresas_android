package com.example.empresas_android.domain.interactor.enterprises

import com.example.empresas_android.data.Response
import com.example.empresas_android.domain.entities.EnterpriseEntity

interface EnterprisesInteractor{
    suspend fun getEnterprises(): Response<List<EnterpriseEntity>>

    suspend fun getEnterpriseById(id:Int): Response<EnterpriseEntity>
}