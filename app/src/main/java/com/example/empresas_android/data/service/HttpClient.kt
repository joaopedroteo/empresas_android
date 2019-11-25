package com.example.empresas_android.data.service

import com.example.empresas_android.data.service.ServiceClientFactory.createOkHttpClient
import com.example.empresas_android.data.service.ServiceClientFactory.provideRetrofit

class RetrofitAnalyzer {

    fun userService(): UserService =
        provideRetrofit(createOkHttpClient()).create(UserService::class.java)

}