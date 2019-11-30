package com.example.empresas_android.data.remote.mapper

import com.example.empresas_android.data.remote.model.request.UserLoginRequest
import com.example.empresas_android.domain.entities.UserLoginEntity

object UserLoginMapper {

    fun fromDomain(entity: UserLoginEntity): UserLoginRequest {
        return UserLoginRequest(
            email = entity.email,
            password = entity.password
        )
    }
}