package com.example.empresas_android.extentions

import com.example.empresas_android.data.Response
import com.example.empresas_android.utils.DataSourceException
import com.example.empresas_android.utils.ErrorMessageEnum
import com.example.empresas_android.utils.ServerError
import kotlinx.coroutines.Deferred
import retrofit2.HttpException
import java.io.IOException

suspend fun <T: Any> apiCall(
    call: suspend () -> Deferred<T>
): Response<T> {
    return try {
        val response = call().await()
        Response.Success(response)
    } catch (httpException: HttpException) {
        return try {
            Response.Failure(DataSourceException())
        } catch (e: Exception) {
            Response.Failure(
                DataSourceException()
            )
        }

    } catch (ioException: IOException) {
        Response.Failure(ServerError(ErrorMessageEnum.INTERNET_ERROR.value))
    } catch (stateException: IllegalStateException) {
        Response.Failure(ServerError())
    } catch (exception: Exception) {
        Response.Failure(ServerError())
    }

}
