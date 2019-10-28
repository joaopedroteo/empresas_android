package com.example.empresas_android.data.service.model

data class PortfolioResponse(
    val enterprises_number: Int,
    val enterprises: List<EnterpriseResponse>
)