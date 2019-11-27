package com.example.empresas_android.data.remote.repository

import com.example.empresas_android.data.remote.mapper.EnterpriseFromByIdMapper
import com.example.empresas_android.data.remote.mapper.EnterprisesMapper
import com.example.empresas_android.domain.entities.UserLoginEntity
import com.example.empresas_android.data.remote.model.ListEnterprisesResponse
import com.example.empresas_android.data.remote.model.LoginResponse
import com.example.empresas_android.data.remote.service.RetrofitAnalizer
import com.example.empresas_android.domain.entities.EnterpriseEntity
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class UserRepositoryImpl : UserRepository {
    override suspend fun signInAsync(userLogin: UserLoginEntity): Deferred<LoginResponse> = withContext(IO) {
        async { RetrofitAnalizer().userService().signInAsync(userLogin).await() }
    }

    override suspend fun getEnterprisesAsync(): Deferred<List<EnterpriseEntity>> = withContext(IO) {
        async {
            val list = RetrofitAnalizer().userService().getEnterprisesAsync().await()
            EnterprisesMapper.toDomain(list)
        }
    }

    override suspend fun getEnterpriseByIdAsync(id: Int): Deferred<EnterpriseEntity> = withContext(IO) {
        async {
            val r = RetrofitAnalizer().userService().getEnterpriseByIdAsync(id).await()
            EnterpriseFromByIdMapper.toDomain(r)
        }
    }

//    = withContext(IO) {
//        async { RetrofitAnalizer().userService().getEnterpriseByIdAsync(id).await() }
//    }

}