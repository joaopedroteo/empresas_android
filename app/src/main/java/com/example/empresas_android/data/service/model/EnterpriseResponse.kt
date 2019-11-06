package com.example.empresas_android.data.service.model

data class EnterpriseResponse(
    val id : Int,
    val enterprise_name : String,
    val photo : String,
    val description : String,
    val city : String,
    val country : String
)
