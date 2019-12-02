package com.example.empresas_android.module

import com.example.empresas_android.data.remote.mapper.EnterprisesMapper
import com.example.empresas_android.domain.interactor.enterprises.EnterprisesInteractor
import com.example.empresas_android.domain.interactor.enterprises.EnterprisesInteractorImpl
import com.example.empresas_android.domain.interactor.user.UserInteractor
import com.example.empresas_android.domain.interactor.user.UserInteractorImpl
import org.koin.dsl.module

val useCasesModule = module {
    factory<UserInteractor> { UserInteractorImpl(get()) }

    factory<EnterprisesInteractor> { EnterprisesInteractorImpl(get()) }

    factory { EnterprisesMapper }
}