package com.example.empresas_android.enterprise

import com.example.empresas_android.data.Response
import com.example.empresas_android.data.remote.repository.enterprise.EnterpriseRepository
import com.example.empresas_android.domain.interactor.enterprises.EnterprisesInteractor
import com.example.empresas_android.domain.interactor.enterprises.EnterprisesInteractorImpl
import com.example.empresas_android.factory.EnterpriseFactory
import com.example.empresas_android.utils.DataSourceException
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class EnterpriseInteractorTest {

    @Mock
    private lateinit var enterprisesRepository: EnterpriseRepository

    private lateinit var enterprisesInteractor: EnterprisesInteractor
    private val mockedId = 2
    private val factory = EnterpriseFactory

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        enterprisesInteractor = EnterprisesInteractorImpl(enterprisesRepository)
    }

    @Test
    fun `getEnterpriseById WHEN repository returns success MUST return success`() {
        val mockedEnterpriseEntity = factory.createEnterpriseEntity()
        runBlocking {
            Mockito.`when`(enterprisesRepository.getEnterpriseByIdAsync(mockedId)).thenReturn(Response.Success(mockedEnterpriseEntity))
            val response = enterprisesInteractor.getEnterpriseById(mockedId)
            assert(response is Response.Success)
            Mockito.verify(enterprisesRepository).getEnterpriseByIdAsync(mockedId)
        }
    }

    @Test
    fun `getEnterpriseById WHEN repository returns failure MUST return failure`() {
        runBlocking {
            Mockito.`when`(enterprisesRepository.getEnterpriseByIdAsync(mockedId)).thenReturn(Response.Failure(DataSourceException()))
            val response = enterprisesInteractor.getEnterpriseById(mockedId)
            assert(response is Response.Failure)
            Mockito.verify(enterprisesRepository).getEnterpriseByIdAsync(mockedId)
        }
    }
}