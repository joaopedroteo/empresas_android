package com.example.empresas_android.module

import com.example.empresas_android.data.remote.service.ServiceClientFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.dsl.module

val webServiceModule = module {
    single { ServiceClientFactory.createClient(get(), get()) }

    single { ServiceClientFactory.createOkHttpClient() }

    factory { CoroutineCallAdapterFactory() }
}