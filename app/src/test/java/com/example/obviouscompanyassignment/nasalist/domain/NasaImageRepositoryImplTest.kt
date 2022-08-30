package com.example.obviouscompanyassignment.nasalist.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.obviouscompanyassignment.nasalist.data.ApiService
import com.example.obviouscompanyassignment.nasalist.domain.NasaImageListMock.getMockResponseData
import com.example.obviouscompanyassignment.nasalist.domain.NasaImageListMock.getMockResponseDataList
import com.example.obviouscompanyassignment.nasalist.repository.NasaImageRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NasaImageRepositoryTest {

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var nasaImageRepository: NasaImageRepository


    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    fun fetchNasaImageDataFromApiService() {
        runBlocking {
            Mockito.`when`(apiService.getNasaImageList()).thenReturn(getMockResponseData())
            val res = apiService.getNasaImageList()
            assertEquals(res.body(), getMockResponseDataList())
        }
    }

    @Test
    fun fetchNasaImageDataFromRepository() {
        runBlocking {
            Mockito.`when`(nasaImageRepository.fetchNasaImageData()).thenReturn(getMockResponseDataList())
            val res = nasaImageRepository.fetchNasaImageData()
            assertEquals(res, getMockResponseDataList())
        }
    }

}