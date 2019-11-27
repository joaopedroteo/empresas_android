package com.example.empresas_android.data.remote.mapper

interface RemoteMapper<E, D> {
    fun toDomain(entity: E): D
}