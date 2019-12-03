package com.example.empresas_android.login

import com.example.empresas_android.data.Response
import com.example.empresas_android.data.remote.repository.user.UserRepository
import com.example.empresas_android.data.remote.repository.user.UserRepositoryImpl
import com.example.empresas_android.data.remote.service.WebService
import com.example.empresas_android.factory.UserFactory
import com.nhaarman.mockitokotlin2.doThrow
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.HttpException

@RunWith(JUnit4::class)
class UserRepositoryImplTest {

    @Mock
    private lateinit var webService: WebService

    private lateinit var userRepository: UserRepository
    private val userLoginRequest by lazy { UserFactory.createValidUserLoginRequest() }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        userRepository =
            UserRepositoryImpl(
                webService
            )
    }

    @Test
    fun `signIn WHEN api returns success MUST return success response`() {
        runBlocking {
            `when`(webService.signInAsync(userLoginRequest)).thenReturn(async { Unit })
            val response = userRepository.signInAsync(userLoginRequest)
            assert(response is Response.Success)
        }
    }

    @Test
    fun `signIn WHEN api returns failure MUST return failure response`() {
        runBlocking{
            doThrow(HttpException::class).`when`(webService).signInAsync((userLoginRequest))
            val response = userRepository.signInAsync(userLoginRequest)
            assert(response is Response.Failure)
        }
    }

}