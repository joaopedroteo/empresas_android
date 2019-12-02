package com.example.empresas_android.data.remote.service

import com.example.empresas_android.data.remote.model.request.UserLoginRequest
import com.example.empresas_android.data.remote.model.response.EnterpriseByIdResponse
import com.example.empresas_android.data.remote.model.response.ListEnterprisesResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface UserService {
    @POST("api/v1/users/auth/sign_in")
    fun signInAsync(@Body userLogin: UserLoginRequest): Deferred<Unit>

//    @GET("/api/v1/enterprises")
//    fun getEnterprises(): Observable<List<EnterpriseResponse>>

    @GET("/api/v1/enterprises")
    fun getEnterprisesAsync(): Deferred<ListEnterprisesResponse>

    @GET("/api/v1/enterprises/{id}")
    fun getEnterpriseByIdAsync(@Path("id") id: Int): Deferred<EnterpriseByIdResponse>
}