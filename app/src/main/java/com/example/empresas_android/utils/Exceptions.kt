package com.example.empresas_android.utils

enum class ErrorMessageEnum(val value: String) {
    INTERNET_ERROR("Sem conexão com a Internet."),
    GENERIC_ERROR("Tivemos um problema de conexão, tente novamente mais tarde"),
    UNAUTHORIZED_ERROR("Login ou senha inválidos")
}

class DataSourceException(message: String = ErrorMessageEnum.GENERIC_ERROR.value) : Exception(message)
class UnauthorizedException(message: String = ErrorMessageEnum.UNAUTHORIZED_ERROR.value): Exception(message)
//class SyncException(message: String = ErrorMessageEnum.GENERIC_ERROR.value) : Exception(message)
class ServerError(message: String = ErrorMessageEnum.GENERIC_ERROR.value) : Exception(message)