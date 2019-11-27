package com.example.empresas_android.domain.entities

data class EnterpriseEntity(
    val id : Int,
    val enterprise_name : String,
    val photo : String,
    val description : String,
    val city : String,
    val country : String
)