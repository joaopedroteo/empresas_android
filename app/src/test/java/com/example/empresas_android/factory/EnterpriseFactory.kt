package com.example.empresas_android.factory

import com.example.empresas_android.data.remote.model.response.EnterpriseByIdResponse
import com.example.empresas_android.data.remote.model.response.EnterpriseResponse
import com.example.empresas_android.data.remote.model.response.ListEnterprisesResponse
import com.example.empresas_android.domain.entities.EnterpriseEntity

object EnterpriseFactory {

    fun createListOfEnterprisesEntity(): List<EnterpriseEntity> {
        return listOf(
            EnterpriseEntity(
                1,
                "name",
                "foto",
                "description",
                "city",
                "country")
        )
    }

    fun createEnterpriseEntity(): EnterpriseEntity {
        return EnterpriseEntity(
            1,
            "name",
            "foto",
            "description",
            "city",
            "country")
    }

    fun createEnterprisesResponse(): ListEnterprisesResponse {
        return ListEnterprisesResponse(
            listOf(createEnterpriseResponse())
        )
    }

    fun createEnterpriseByIdResponse(): EnterpriseByIdResponse {
        return EnterpriseByIdResponse(
            createEnterpriseResponse()
        )
    }

    private fun createEnterpriseResponse(): EnterpriseResponse {
        return EnterpriseResponse(
            1,
            "name",
            "foto",
            "description",
            "city",
            "country")
    }
}