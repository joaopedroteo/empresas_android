package com.example.empresas_android.data.remote.mapper

import com.example.empresas_android.data.remote.model.EnterpriseByIdResponse
import com.example.empresas_android.domain.entities.EnterpriseEntity

object EnterpriseFromByIdMapper : RemoteMapper<EnterpriseByIdResponse, EnterpriseEntity> {
    override fun toDomain(entity: EnterpriseByIdResponse): EnterpriseEntity {
        return EnterpriseEntity(
            id = entity.enterprise.id,
            enterprise_name = entity.enterprise.enterprise_name,
            photo = entity.enterprise.photo,
            description = entity.enterprise.description,
            country = entity.enterprise.country,
            city = entity.enterprise.city
        )
    }

}