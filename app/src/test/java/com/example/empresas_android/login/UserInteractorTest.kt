package com.example.empresas_android.login

import com.example.empresas_android.data.Response
import com.example.empresas_android.data.remote.repository.user.UserRepository
import com.example.empresas_android.domain.interactor.user.UserInteractor
import com.example.empresas_android.domain.interactor.user.UserInteractorImpl
import com.example.empresas_android.factory.UserFactory
import com.example.empresas_android.utils.DataSourceException
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class UserInteractorTest {

    @Mock private lateinit var userRepository: UserRepository

    private lateinit var userInteractor: UserInteractor

    private val factory = UserFactory

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        userInteractor = UserInteractorImpl(userRepository)
    }

    @Test
    fun `signIn WHEN repository returns a success response MUST return a success response`() {
        val mockedUserLoginRequest = factory.createValidUserLoginRequest()
        val mockedUserLoginEntity = factory.createValidUserLoginEntity()
        runBlocking {
            `when`(userRepository.signInAsync(mockedUserLoginRequest)).thenReturn(Response.Success(Unit))
            val response = userInteractor.signIn(mockedUserLoginEntity)
            assert(response is Response.Success)
            verify(userRepository).signInAsync(mockedUserLoginRequest)
        }
    }

    @Test
    fun `signIn WHEN repository returns a failure response MUST return a failure response`() {
        val mockedUserLoginRequest = factory.createValidUserLoginRequest()
        val mockedUserLoginEntity = factory.createValidUserLoginEntity()
        runBlocking {
            `when`(userRepository.signInAsync(mockedUserLoginRequest)).thenReturn(Response.Failure(DataSourceException()))
            val response = userInteractor.signIn(mockedUserLoginEntity)
            assert(response is Response.Failure)
            verify(userRepository).signInAsync(mockedUserLoginRequest)
        }
    }


}
