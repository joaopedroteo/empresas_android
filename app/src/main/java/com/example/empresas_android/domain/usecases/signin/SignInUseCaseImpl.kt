package com.example.empresas_android.domain.usecases.signin

import com.example.empresas_android.domain.entities.UserLoginEntity
import com.example.empresas_android.data.remote.model.LoginResponse
import com.example.empresas_android.data.remote.repository.UserRepositoryImpl

class SignInUseCaseImpl : SignInUseCase {
    private val repository = UserRepositoryImpl()

    override suspend fun signIn(userLogin: UserLoginEntity): LoginResponse {
        return repository.signInAsync(userLogin).await()
    }

}