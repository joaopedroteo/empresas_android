package com.example.empresas_android.data.remote.repository

import com.example.empresas_android.data.Response
import com.example.empresas_android.data.remote.mapper.EnterpriseFromByIdMapper
import com.example.empresas_android.data.remote.mapper.EnterprisesMapper
import com.example.empresas_android.data.remote.model.request.UserLoginRequest
import com.example.empresas_android.data.remote.model.response.LoginResponse
import com.example.empresas_android.data.remote.service.UserService
import com.example.empresas_android.domain.entities.EnterpriseEntity
import com.example.empresas_android.extentions.apiCall
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val userService: UserService
) : UserRepository {



    override suspend fun signInAsync(userLogin: UserLoginRequest): Response<LoginResponse> =
        apiCall {
            userService.signInAsync(userLogin)
        }

    override suspend fun getEnterprisesAsync(): Response<List<EnterpriseEntity>> = withContext(IO) {
        val response = apiCall {
            userService.getEnterprisesAsync()
        }
        when (response) {
            is Response.Success -> Response.Success(EnterprisesMapper.toDomain(response.data))
            is Response.Failure -> Response.Failure(response.throwable)
        }
    }

    override suspend fun getEnterpriseByIdAsync(id: Int): Response<EnterpriseEntity> =
        withContext(IO) {
            val response = apiCall {
                userService.getEnterpriseByIdAsync(id)
            }
            when (response) {
                is Response.Success -> Response.Success(EnterpriseFromByIdMapper.toDomain(response.data))
                is Response.Failure -> Response.Failure(response.throwable)
            }
        }

}