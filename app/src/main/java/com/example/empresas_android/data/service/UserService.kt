package com.example.empresas_android.data.service

import com.example.empresas_android.Constants
import com.example.empresas_android.data.service.model.request.UserLoginRequest
import com.example.empresas_android.data.service.model.response.EnterpriseByIdResponse
import com.example.empresas_android.data.service.model.response.ListEnterprisesResponse
import com.example.empresas_android.data.service.model.response.LoginResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface UserService{
    @POST(Constants.Service.SIGN_IN)
    fun signIn(@Body userLoginRequest: UserLoginRequest): Observable<LoginResponse>

    @GET(Constants.Service.ENTERPRISES)
    fun getEnterprises(): Observable<ListEnterprisesResponse>

    @GET(Constants.Service.ENTERPRISES_ID)
    fun getEnterpriseById(@Path("id") id:Int): Observable<EnterpriseByIdResponse>
}