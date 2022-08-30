package com.example.obviouscompanyassignment.nasalist.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.obviouscompanyassignment.nasalist.data.ApiService
import com.example.obviouscompanyassignment.nasalist.repository.NasaImageRepository
import com.example.obviouscompanyassignment.nasalist.repository.NasaImageRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
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
class NasaImageViewModelTest {

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var nasaImageRepository: NasaImageRepository
    lateinit var nasaImageRepositoryImpl: NasaImageRepositoryImpl


    lateinit var nasaImageViewModel: NasaImageViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        nasaImageViewModel = NasaImageViewModel(nasaImageRepository)
        nasaImageRepositoryImpl= NasaImageRepositoryImpl(apiService)
    }

    @Test
    fun fetchNasaImageDataAsNull() {
        runBlocking {
            Mockito.`when`(nasaImageRepository.fetchNasaImageData())
                .thenReturn(null)
            nasaImageViewModel.fetchNasaImageListData()
            assertEquals(nasaImageViewModel.imageList.value, null)
        }
    }

}