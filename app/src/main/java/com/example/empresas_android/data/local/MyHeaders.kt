package com.example.empresas_android.data.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyHeaders(
    val accessToken: String,
    val client: String,
    val uid: String

) : Parcelable