package com.example.empresas_android.data.remote.repository.enterprise

import com.example.empresas_android.data.Response
import com.example.empresas_android.data.remote.mapper.EnterpriseFromByIdMapper
import com.example.empresas_android.data.remote.mapper.EnterprisesMapper
import com.example.empresas_android.data.remote.repository.enterprise.EnterpriseRepository
import com.example.empresas_android.data.remote.service.WebService
import com.example.empresas_android.domain.entities.EnterpriseEntity
import com.example.empresas_android.extentions.apiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EnterpriseRepositoryImpl(
    private val webService: WebService
) : EnterpriseRepository {
    override suspend fun getEnterprisesAsync(): Response<List<EnterpriseEntity>> = withContext(
        Dispatchers.IO
    ) {
        val response = apiCall {
            webService.getEnterprisesAsync()
        }
        when (response) {
            is Response.Success -> Response.Success(EnterprisesMapper.toDomain(response.data))
            is Response.Failure -> Response.Failure(response.throwable)
        }
    }

    override suspend fun getEnterpriseByIdAsync(id: Int): Response<EnterpriseEntity> =
        withContext(Dispatchers.IO) {
            val response = apiCall {
                webService.getEnterpriseByIdAsync(id)
            }
            when (response) {
                is Response.Success -> Response.Success(EnterpriseFromByIdMapper.toDomain(response.data))
                is Response.Failure -> Response.Failure(response.throwable)
            }
        }

}