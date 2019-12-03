package com.example.empresas_android.enterprisesList

import com.example.empresas_android.data.Response
import com.example.empresas_android.data.remote.repository.enterprise.EnterpriseRepository
import com.example.empresas_android.data.remote.repository.enterprise.EnterpriseRepositoryImpl
import com.example.empresas_android.data.remote.service.WebService
import com.example.empresas_android.factory.EnterpriseFactory
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
class EnterprisesRepositoryTest{

    @Mock
    private lateinit var webService: WebService

    private var factory = EnterpriseFactory

    private lateinit var enterpriseRepository: EnterpriseRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        enterpriseRepository =
            EnterpriseRepositoryImpl(
                webService
            )
    }

    @Test
    fun `getEnterprises WHEN api returns success response MUST return success response`() {
        val mockedEnterpriseList = factory.createEnterprisesResponse()
        runBlocking {
            `when`(webService.getEnterprisesAsync()).thenReturn(async { mockedEnterpriseList })
            val response = enterpriseRepository.getEnterprisesAsync()
            assert(response is Response.Success)
        }
    }

    @Test
    fun `getEnterprises WHEN api returns failure response MUST return failure response`() {
        runBlocking {
            `when`(webService.getEnterprisesAsync()).thenReturn(null)
            val response = enterpriseRepository.getEnterprisesAsync()
            assert(response is Response.Failure)
        }
    }
}