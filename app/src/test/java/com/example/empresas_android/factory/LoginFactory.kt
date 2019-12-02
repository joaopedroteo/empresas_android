package com.example.empresas_android.factory

import com.example.empresas_android.data.remote.model.request.UserLoginRequest
import com.example.empresas_android.data.remote.model.response.EnterpriseResponse
import com.example.empresas_android.domain.entities.UserLoginEntity

object LoginFactory {

    private fun mockEnterpriseRespone() = EnterpriseResponse(
        1,
        "Nome da empresa",
        "photo",
        "Essa é a descrição da empresa",
        "Lavras",
        "Brasil"
    )

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