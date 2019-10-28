package com.example.empresas_android.data.service.model

data class InvestorResponse(
    val id: Int,
    val investor_name: String,
    val email: String,
    val city: String,
    val country: String,
    val balance: Double,
    val photo: String,
    val portifolio: PortfolioResponse,
    val portifolio_value: Double,
    val first_access: Boolean,
    val super_angel: Boolean
)
