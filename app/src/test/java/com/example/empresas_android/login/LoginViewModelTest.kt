package com.example.empresas_android.login

import com.example.empresas_android.data.Response
import com.example.empresas_android.domain.interactor.user.UserInteractor
import com.example.empresas_android.factory.LoginFactory
import com.example.empresas_android.presentation.LoginViewModel
import com.example.empresas_android.utils.ThreadContextProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class LoginViewModelTest: KoinTest {
    private lateinit var viewModel: LoginViewModel
    private val factory = LoginFactory

    @Mock
    private lateinit var userInteractor: UserInteractor

    @Mock
    private val userLogin = factory.createValidUserLoginEntity()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = LoginViewModel(userInteractor, ThreadContextProvider())
    }

    @Test
    fun `signIn WHEN interactor returns success response MUST set loginLiveData true`() {
        runBlocking {
            doReturn(Response.Success(Unit)).`when`(userInteractor).signIn(userLogin)
            viewModel.loginApi(userLogin)
        }
        assert(viewModel.loginLiveData.value == true)
    }

    @Test
    fun `signIn WHEN interactor returns failure response MUST set errorConection true`(){

    }

}