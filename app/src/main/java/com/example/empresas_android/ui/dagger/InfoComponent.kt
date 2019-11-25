package com.example.empresas_android.ui.dagger

import com.example.empresas_android.presentation.EnterpriseDetailViewModel
import dagger.Component

@Component
interface InfoComponent {

    fun inject(enterpriseDetailViewModel: EnterpriseDetailViewModel)
}