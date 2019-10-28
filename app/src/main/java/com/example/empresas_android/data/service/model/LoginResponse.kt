package com.example.empresas_android.data.service.model

data class LoginResponse(
    val investorResponse: InvestorResponse,
    val enterpriseResponse: EnterpriseResponse,
    val success: Boolean
)