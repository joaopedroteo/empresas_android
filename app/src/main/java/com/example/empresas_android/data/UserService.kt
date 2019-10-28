package com.example.empresas_android.data

import retrofit2.Call
import retrofit2.http.POST

interface UserService{
    @POST("api/v1/users/auth/sign_in")
    fun signIn(): Call<String>
}