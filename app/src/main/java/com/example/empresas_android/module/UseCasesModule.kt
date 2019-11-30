package com.example.empresas_android.module

import com.example.empresas_android.data.remote.mapper.EnterprisesMapper
import com.example.empresas_android.domain.useCases.enterprises.EnterprisesUseCases
import com.example.empresas_android.domain.useCases.enterprises.EnterprisesUseCasesImpl
import com.example.empresas_android.domain.useCases.signIn.SignInUseCase
import com.example.empresas_android.domain.useCases.signIn.SignInUseCaseImpl
import org.koin.dsl.module

val useCasesModule = module {
    factory<SignInUseCase> { SignInUseCaseImpl(get()) }

    factory<EnterprisesUseCases> { EnterprisesUseCasesImpl(get()) }

    factory { EnterprisesMapper }
}