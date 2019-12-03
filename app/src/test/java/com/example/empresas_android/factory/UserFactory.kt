package com.example.empresas_android.factory

import com.example.empresas_android.data.remote.model.request.UserLoginRequest
import com.example.empresas_android.domain.entities.UserLoginEntity

object UserFactory {

    fun createValidUserLoginRequest() =
        UserLoginRequest(
            "teste@ioasys.com.br",
            "12341234"
        )

    fun createValidUserLoginEntity() =
        UserLoginEntity(
            "teste@ioasys.com.br",
            "12341234"
        )
}