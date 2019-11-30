package com.example.empresas_android.login

import com.example.empresas_android.data.Response
import com.example.empresas_android.data.remote.repository.UserRepository
import com.example.empresas_android.domain.useCases.signIn.SignInUseCase
import com.example.empresas_android.domain.useCases.signIn.SignInUseCaseImpl
import com.example.empresas_android.factory.LoginFactory
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class UserUseCaseCaseImpl {

    @Mock private lateinit var userRepository: UserRepository

    private lateinit var signInUserCase: SignInUseCase

    private val factory = LoginFactory

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        signInUserCase = SignInUseCaseImpl(userRepository)
    }

    @Test
    fun `signIn WHEN repository returns a success response MUST return a success return`() {
        val mockedUserLoginRequest = factory.createValidUserLoginRequest()
        val mockedUserLoginEntity = factory.createValidUserLoginEntity()
        val mockedResponse = factory.mockLoginResponse()
        runBlocking {
            doReturn(mockedResponse).`when`(userRepository).signInAsync(mockedUserLoginRequest)
            assert(signInUserCase.signIn(mockedUserLoginEntity) is Response.Success)
            verify(userRepository).signInAsync(mockedUserLoginRequest)
        }
    }


}
