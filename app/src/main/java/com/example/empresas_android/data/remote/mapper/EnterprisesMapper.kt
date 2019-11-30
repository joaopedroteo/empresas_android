package com.example.empresas_android.data.remote.mapper

import com.example.empresas_android.data.remote.model.response.ListEnterprisesResponse
import com.example.empresas_android.domain.entities.EnterpriseEntity

object EnterprisesMapper : RemoteMapper<ListEnterprisesResponse, List<EnterpriseEntity>> {
    override fun toDomain(entity: ListEnterprisesResponse): List<EnterpriseEntity> {
        val list : MutableList<EnterpriseEntity> = mutableListOf()
        entity.enterprises.forEach {
            list.add(EnterpriseFromResponseMapper.toDomain(it))
        }
        return list
    }

}