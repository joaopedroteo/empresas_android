package com.example.empresas_android.enterprise

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
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class EnterpriseRepositoryTest {

    @Mock
    private lateinit var webService: WebService

    private var factory = EnterpriseFactory
    private val mockedId = 2
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
    fun `getEnterpriseById WHEN api returns success response MUST return success response`() {
        val mockedEnterpriseResponse = factory.createEnterpriseByIdResponse()
        runBlocking {
            Mockito.`when`(webService.getEnterpriseByIdAsync(mockedId)).thenReturn(async { mockedEnterpriseResponse })
            val response = enterpriseRepository.getEnterpriseByIdAsync(mockedId)
            assert(response is Response.Success)
        }
    }

    @Test
    fun `getEnterpriseById WHEN api returns failure response MUST return failure response`() {
        runBlocking {
            Mockito.`when`(webService.getEnterpriseByIdAsync(mockedId)).thenReturn(null)
            val response = enterpriseRepository.getEnterpriseByIdAsync(mockedId)
            assert(response is Response.Failure)
        }
    }
}