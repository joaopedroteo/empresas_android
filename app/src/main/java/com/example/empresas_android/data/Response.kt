package com.example.empresas_android.data

sealed class Response<out T> {
    class Success<out T>(val data: T) : Response<T>()
    class Failure(val throwable: Throwable) : Response<Nothing>()
}