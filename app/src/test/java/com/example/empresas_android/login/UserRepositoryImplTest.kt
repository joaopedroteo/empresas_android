package com.example.empresas_android.login

import com.example.empresas_android.data.Response
import com.example.empresas_android.data.remote.repository.UserRepository
import com.example.empresas_android.data.remote.repository.UserRepositoryImpl
import com.example.empresas_android.data.remote.service.UserService
import com.example.empresas_android.factory.LoginFactory
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class UserRepositoryImplTest {

    @Mock
    private lateinit var userService: UserService

    private lateinit var userRepository: UserRepository
    private val mockedLoginResponse by lazy { LoginFactory.mockLoginResponse() }
    private val userLoginResquest by lazy { LoginFactory.createValidUserLoginRequest() }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        userRepository = UserRepositoryImpl(userService)
    }

    @Test
    fun `signIn WHEN api returns success MUST return success response`() {
        runBlocking {
            `when`(userService.signInAsync(userLoginResquest)).thenReturn(async { mockedLoginResponse })
            val response = userRepository.signInAsync(userLoginResquest)
            assert(response is Response.Success)
        }
    }

    @Test
    fun `signIn WHEN api returns failure MUST return failure response`() {
        runBlocking{
            val response = userRepository.signInAsync(userLoginResquest)
            assert(response is Response.Failure)
        }
    }

}