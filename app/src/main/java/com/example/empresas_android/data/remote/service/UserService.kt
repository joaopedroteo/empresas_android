package com.example.empresas_android.data.remote.service

import com.example.empresas_android.domain.entities.UserLoginEntity
import com.example.empresas_android.data.remote.model.EnterpriseByIdResponse
import com.example.empresas_android.data.remote.model.ListEnterprisesResponse
import com.example.empresas_android.data.remote.model.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.*


interface UserService{
    @POST("api/v1/users/auth/sign_in")
    fun signInAsync(@Body userLogin: UserLoginEntity): Deferred<LoginResponse>

//    @GET("/api/v1/enterprises")
//    fun getEnterprises(): Observable<List<EnterpriseResponse>>

    @GET("/api/v1/enterprises")
    fun getEnterprisesAsync(): Deferred<ListEnterprisesResponse>

    @GET("/api/v1/enterprises/{id}")
    fun getEnterpriseByIdAsync(@Path("id") id:Int): Deferred<EnterpriseByIdResponse>
}