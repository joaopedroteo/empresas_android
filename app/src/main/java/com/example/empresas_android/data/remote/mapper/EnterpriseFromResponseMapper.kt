package com.example.empresas_android.data.remote.mapper

import com.example.empresas_android.data.remote.model.response.EnterpriseResponse
import com.example.empresas_android.domain.entities.EnterpriseEntity

object EnterpriseFromResponseMapper : RemoteMapper<EnterpriseResponse, EnterpriseEntity> {
    override fun toDomain(entity: EnterpriseResponse): EnterpriseEntity {
        return EnterpriseEntity(
            id = entity.id,
            enterprise_name = entity.enterprise_name,
            description = entity.description,
            photo = entity.photo,
            city = entity.city,
            country = entity.country
        )
    }
}