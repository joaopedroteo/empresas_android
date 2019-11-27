package com.example.empresas_android.data.remote.mapper

import androidx.lifecycle.MutableLiveData
import com.example.empresas_android.data.remote.model.ListEnterprisesResponse
import com.example.empresas_android.domain.entities.EnterpriseEntity

object EnterprisesMapper : RemoteMapper<ListEnterprisesResponse, List<EnterpriseEntity>> {
    override fun toDomain(entity: ListEnterprisesResponse): List<EnterpriseEntity> {
        val list = MutableLiveData<List<EnterpriseEntity>>()
        entity.enterprises.forEach {
            list.value = list.value?.plus((EnterpriseFromResponseMapper.toDomain(it)))
        }
        return list.value!!
    }

}