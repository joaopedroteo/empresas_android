package com.example.empresas_android.module

import com.example.empresas_android.utils.ThreadContextProvider
import org.koin.dsl.module

val generalModule = module {

    factory { ThreadContextProvider() }

}