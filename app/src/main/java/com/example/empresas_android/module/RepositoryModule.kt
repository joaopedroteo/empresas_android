package com.example.empresas_android.module

import com.example.empresas_android.data.remote.repository.UserRepository
import com.example.empresas_android.data.remote.repository.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<UserRepository> { UserRepositoryImpl(get()) }
}