package com.example.empresas_android.enterprisesList

import com.example.empresas_android.data.Response
import com.example.empresas_android.data.remote.repository.enterprise.EnterpriseRepository
import com.example.empresas_android.domain.interactor.enterprises.EnterprisesInteractor
import com.example.empresas_android.domain.interactor.enterprises.EnterprisesInteractorImpl
import com.example.empresas_android.factory.EnterpriseFactory
import com.example.empresas_android.utils.DataSourceException
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class EnterprisesInteractorTest {

    @Mock
    private lateinit var enterprisesRepository: EnterpriseRepository

    private lateinit var enterprisesInteractor: EnterprisesInteractor

    private val factory = EnterpriseFactory

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        enterprisesInteractor = EnterprisesInteractorImpl(enterprisesRepository)
    }

    @Test
    fun `getEnterprises WHEN repository returns success MUST return success`() {
        val mockedEnterprises = factory.createListOfEnterprisesEntity()
        runBlocking {
            `when`(enterprisesRepository.getEnterprisesAsync()).thenReturn(Response.Success(mockedEnterprises))
            val response = enterprisesInteractor.getEnterprises()
            assert(response is Response.Success)
            verify(enterprisesRepository).getEnterprisesAsync()
        }
    }

    @Test
    fun `getEnterprises WHEN repository returns failure MUST return failure`() {
        runBlocking {
            `when`(enterprisesRepository.getEnterprisesAsync()).thenReturn(Response.Failure(DataSourceException()))
            val response = enterprisesInteractor.getEnterprises()
            assert(response is Response.Failure)
            verify(enterprisesRepository).getEnterprisesAsync()
        }
    }
}