package com.example.empresas_android.domain.interactor.user

import com.example.empresas_android.data.Response
import com.example.empresas_android.data.remote.mapper.UserLoginMapper
import com.example.empresas_android.data.remote.repository.UserRepository
import com.example.empresas_android.domain.entities.UserLoginEntity

class UserInteractorImpl(
    private val repository: UserRepository
) : UserInteractor {

    override suspend fun signIn(userLogin: UserLoginEntity): Response<Unit> {
        return repository.signInAsync( UserLoginMapper.fromDomain(userLogin) )
    }

}