package com.example.empresas_android.data.remote.repository

import com.example.empresas_android.domain.entities.UserLoginEntity
import com.example.empresas_android.data.remote.model.EnterpriseByIdResponse
import com.example.empresas_android.data.remote.model.ListEnterprisesResponse
import com.example.empresas_android.data.remote.model.LoginResponse
import com.example.empresas_android.data.remote.service.RetrofitAnalizer
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class UserRepositoryImpl : UserRepository {
    override suspend fun signInAsync(userLogin: UserLoginEntity): Deferred<LoginResponse> = withContext(IO) {
        async { RetrofitAnalizer().userService().signInAsync(userLogin).await() }
    }

    override suspend fun getEnterprisesAsync(): Deferred<ListEnterprisesResponse> = withContext(IO) {
        async { RetrofitAnalizer().userService().getEnterprisesAsync().await() }
    }

    override suspend fun getEnterpriseByIdAsync(id: Int): Deferred<EnterpriseByIdResponse> = withContext(IO) {
        async { RetrofitAnalizer().userService().getEnterpriseByIdAsync(id).await() }
    }

}