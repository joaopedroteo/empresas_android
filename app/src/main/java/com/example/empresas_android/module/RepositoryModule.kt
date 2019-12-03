package com.example.empresas_android.module

import com.example.empresas_android.data.remote.repository.enterprise.EnterpriseRepository
import com.example.empresas_android.data.remote.repository.enterprise.EnterpriseRepositoryImpl
import com.example.empresas_android.data.remote.repository.user.UserRepository
import com.example.empresas_android.data.remote.repository.user.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<UserRepository> {
        UserRepositoryImpl(
            get()
        )
    }

    factory<EnterpriseRepository> {
        EnterpriseRepositoryImpl(
            get()
        )
    }
}