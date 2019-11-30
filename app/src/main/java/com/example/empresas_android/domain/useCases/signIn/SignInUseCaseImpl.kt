package com.example.empresas_android.domain.useCases.signIn

import com.example.empresas_android.data.Response
import com.example.empresas_android.data.remote.mapper.UserLoginMapper
import com.example.empresas_android.data.remote.model.response.LoginResponse
import com.example.empresas_android.data.remote.repository.UserRepository
import com.example.empresas_android.domain.entities.UserLoginEntity

class SignInUseCaseImpl(
    private val repository: UserRepository
) : SignInUseCase {

    override suspend fun signIn(userLogin: UserLoginEntity): Response<LoginResponse> {
        return repository.signInAsync( UserLoginMapper.fromDomain(userLogin) )
    }

}