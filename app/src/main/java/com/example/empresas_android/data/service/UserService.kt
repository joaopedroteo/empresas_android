package com.example.empresas_android.data.service

import com.example.empresas_android.data.local.UserLogin
import com.example.empresas_android.data.service.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService{
    @POST("api/v1/users/auth/sign_in")
    fun signIn(@Body userLogin: UserLogin): Call<LoginResponse>
}