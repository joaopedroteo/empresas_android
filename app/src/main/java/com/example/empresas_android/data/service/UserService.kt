package com.example.empresas_android.data.service

import com.example.empresas_android.data.local.UserLogin
import com.example.empresas_android.data.service.model.EnterpriseByIdResponse
import com.example.empresas_android.data.service.model.ListEnterprisesResponse
import com.example.empresas_android.data.service.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface UserService{
    @POST("api/v1/users/auth/sign_in")
    fun signIn(@Body userLogin: UserLogin): io.reactivex.Observable<LoginResponse>

//    @GET("/api/v1/enterprises")
//    fun getEnterprises(): Observable<List<EnterpriseResponse>>

    @GET("/api/v1/enterprises")
    fun getEnterprises(): Call<ListEnterprisesResponse>

    @GET("/api/v1/enterprises/{id}")
    fun getEnterpriseById(@Path("id") id:Int): Call<EnterpriseByIdResponse>
}