package com.example.empresas_android.data.local.preferences

import okhttp3.Headers


interface PreferencesRepository {
    fun getCredentials(): HashMap<String, String>

    fun saveCredentials(headers: Headers)

    fun hasValidCredentials(): Boolean

    fun deleteCredentials()
}