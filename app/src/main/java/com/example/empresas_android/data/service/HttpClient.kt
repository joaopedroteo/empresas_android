package com.example.empresas_android.data.service

import com.example.empresas_android.data.local.preferences.MyPreferences
import com.example.empresas_android.data.service.ServiceClientFactory.createOkHttpClient
import com.example.empresas_android.data.service.ServiceClientFactory.provideRetrofit

class RetrofitAnalyzer {

    fun userService(myPreferences: MyPreferences): UserService =
        provideRetrofit(createOkHttpClient(myPreferences)).create(UserService::class.java)

}