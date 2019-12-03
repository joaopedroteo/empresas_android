package com.example.empresas_android.login

import com.example.empresas_android.data.Response
import com.example.empresas_android.domain.interactor.user.UserInteractor
import com.example.empresas_android.factory.UserFactory
import com.example.empresas_android.presentation.LoginViewModel
import com.example.empresas_android.utils.TestContextProvider
import com.example.empresas_android.utils.ThreadContextProvider
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class LoginViewModelTest: KoinTest {

    private lateinit var viewModel: LoginViewModel
    private val factory = UserFactory

    @Mock
    private lateinit var userInteractor: UserInteractor

    @Mock
    private val userLogin = factory.createValidUserLoginEntity()

    private val contextProvider = TestContextProvider() as ThreadContextProvider
    private val moduleContextProvider = module { single { contextProvider } }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        startKoin {
            loadKoinModules(listOf(moduleContextProvider))
        }
        viewModel = LoginViewModel(userInteractor, contextProvider)
    }

    @Test
    fun `signIn WHEN interactor returns success response MUST set loginLiveData true`() {
        runBlocking {
            `when`(userInteractor.signIn(userLogin)).thenReturn(Response.Success(Unit))
            viewModel.loginApi(userLogin)
        }
            assertEquals(viewModel.loginLiveData.value, true)
    }

}