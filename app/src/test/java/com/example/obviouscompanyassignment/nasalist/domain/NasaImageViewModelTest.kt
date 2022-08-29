package com.example.obviouscompanyassignment.nasalist.domain

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.obviouscompanyassignment.base.network.DataResult
import com.example.obviouscompanyassignment.nasalist.data.ApiService
import com.example.obviouscompanyassignment.nasalist.data.apimodel.NasaImageResponseItem
import com.example.obviouscompanyassignment.nasalist.repository.NasaImageRepositoryImpl
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class NasaImageViewModelTest {

    @Mock
    lateinit var apiService: ApiService

    private val testDispatcher = TestCoroutineDispatcher()
    lateinit var nasaImageViewModel: NasaImageViewModel
    lateinit var nasaImageRepositoryImpl: NasaImageRepositoryImpl

    @Mock
    lateinit var observer: Observer<DataResult<List<NasaImageResponseItem>>>

    @Captor
    lateinit var argumentCaptor: ArgumentCaptor<DataResult<List<NasaImageResponseItem>>>


    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this);
        nasaImageRepositoryImpl = NasaImageRepositoryImpl(apiService)
        nasaImageViewModel = NasaImageViewModel(nasaImageRepositoryImpl)
    }

    @Test
    fun fetchNasaImageData1() {
        runBlocking {
            Mockito.`when`(nasaImageRepositoryImpl.fetchNasaImageData())
                .thenReturn(getMockResponseDataList())

            Mockito.verify(observer, Mockito.times(2)).onChanged(argumentCaptor.capture())
            nasaImageViewModel.imageList.observeForever(observer)
            nasaImageViewModel.fetchNasaImageListData()
            val  values=argumentCaptor.allValues
            assertEquals(DataResult.Loading, values[0])
        }
    }


    fun getMockResponseDataList(): List<NasaImageResponseItem> {
        return listOf<NasaImageResponseItem>(
            NasaImageResponseItem(
                copyright = "ESA/HubbleNASA",
                date = "2019-12-01",
                explanation = "Why does this galaxy have a ring of bright blue stars?  Beautiful island universe Messier 94 lies a mere 15 million light-years distant in the northern constellation of the Hunting Dogs (Canes Venatici). A popular target for Earth-based astronomers, the face-on spiral galaxy is about 30,000 light-years across, with spiral arms sweeping through the outskirts of its broad disk. But this Hubble Space Telescope field of view spans about 7,000 light-years across M94's central region. The featured close-up highlights the galaxy's compact, bright nucleus, prominent inner dust lanes, and the remarkable bluish ring of young massive stars. The ring stars are all likely less than 10 million years old, indicating that M94 is a starburst galaxy that is experiencing an epoch of rapid star formation from inspiraling gas. The circular ripple of blue stars is likely a wave propagating outward, having been triggered by the gravity and rotation of a oval matter distributions. Because M94 is relatively nearby, astronomers can better explore details of its starburst ring. Astrophysicists: Browse 2,000+ codes in the Astrophysics Source Code Library",
                hdurl = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_1002.jpg",
                media_type = "image",
                service_version = "v1",
                title = "Starburst Galaxy M94 from Hubble",
                url = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg"
            ),
            NasaImageResponseItem(
                copyright = "Steve Mazlin",
                date = "2019-12-03",
                explanation = "Why does this galaxy have a ring of bright blue stars?  Beautiful island universe Messier 94 lies a mere 15 million light-years distant in the northern constellation of the Hunting Dogs (Canes Venatici). A popular target for Earth-based astronomers, the face-on spiral galaxy is about 30,000 light-years across, with spiral arms sweeping through the outskirts of its broad disk. But this Hubble Space Telescope field of view spans about 7,000 light-years across M94's central region. The featured close-up highlights the galaxy's compact, bright nucleus, prominent inner dust lanes, and the remarkable bluish ring of young massive stars. The ring stars are all likely less than 10 million years old, indicating that M94 is a starburst galaxy that is experiencing an epoch of rapid star formation from inspiraling gas. The circular ripple of blue stars is likely a wave propagating outward, having been triggered by the gravity and rotation of a oval matter distributions. Because M94 is relatively nearby, astronomers can better explore details of its starburst ring. Astrophysicists: Browse 2,000+ codes in the Astrophysics Source Code Library",
                hdurl = "https://apod.nasa.gov/apod/image/1912/M27_Mazlin_2000.jpg",
                media_type = "image",
                service_version = "v1",
                title = "M27: The Dumbbell Nebula",
                url = "https://apod.nasa.gov/apod/image/1912/M27_Mazlin_960.jpg"
            )
        )
    }

    fun getMockLiveData(): DataResult.Success<List<NasaImageResponseItem>> {
        val mockNasaImageList = DataResult.Success(
            listOf<NasaImageResponseItem>(
                NasaImageResponseItem(
                    copyright = "ESA/HubbleNASA",
                    date = "2019-12-01",
                    explanation = "Why does this galaxy have a ring of bright blue stars?  Beautiful island universe Messier 94 lies a mere 15 million light-years distant in the northern constellation of the Hunting Dogs (Canes Venatici). A popular target for Earth-based astronomers, the face-on spiral galaxy is about 30,000 light-years across, with spiral arms sweeping through the outskirts of its broad disk. But this Hubble Space Telescope field of view spans about 7,000 light-years across M94's central region. The featured close-up highlights the galaxy's compact, bright nucleus, prominent inner dust lanes, and the remarkable bluish ring of young massive stars. The ring stars are all likely less than 10 million years old, indicating that M94 is a starburst galaxy that is experiencing an epoch of rapid star formation from inspiraling gas. The circular ripple of blue stars is likely a wave propagating outward, having been triggered by the gravity and rotation of a oval matter distributions. Because M94 is relatively nearby, astronomers can better explore details of its starburst ring. Astrophysicists: Browse 2,000+ codes in the Astrophysics Source Code Library",
                    hdurl = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_1002.jpg",
                    media_type = "image",
                    service_version = "v1",
                    title = "Starburst Galaxy M94 from Hubble",
                    url = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg"
                ),
                NasaImageResponseItem(
                    copyright = "Steve Mazlin",
                    date = "2019-12-03",
                    explanation = "Why does this galaxy have a ring of bright blue stars?  Beautiful island universe Messier 94 lies a mere 15 million light-years distant in the northern constellation of the Hunting Dogs (Canes Venatici). A popular target for Earth-based astronomers, the face-on spiral galaxy is about 30,000 light-years across, with spiral arms sweeping through the outskirts of its broad disk. But this Hubble Space Telescope field of view spans about 7,000 light-years across M94's central region. The featured close-up highlights the galaxy's compact, bright nucleus, prominent inner dust lanes, and the remarkable bluish ring of young massive stars. The ring stars are all likely less than 10 million years old, indicating that M94 is a starburst galaxy that is experiencing an epoch of rapid star formation from inspiraling gas. The circular ripple of blue stars is likely a wave propagating outward, having been triggered by the gravity and rotation of a oval matter distributions. Because M94 is relatively nearby, astronomers can better explore details of its starburst ring. Astrophysicists: Browse 2,000+ codes in the Astrophysics Source Code Library",
                    hdurl = "https://apod.nasa.gov/apod/image/1912/M27_Mazlin_2000.jpg",
                    media_type = "image",
                    service_version = "v1",
                    title = "M27: The Dumbbell Nebula",
                    url = "https://apod.nasa.gov/apod/image/1912/M27_Mazlin_960.jpg"
                )
            )
        )
        return mockNasaImageList
    }

    fun getMockResponseData(): Response<List<NasaImageResponseItem>> {
        val mockNasaImageList = Response.success(
            listOf<NasaImageResponseItem>(
                NasaImageResponseItem(
                    copyright = "ESA/HubbleNASA",
                    date = "2019-12-01",
                    explanation = "Why does this galaxy have a ring of bright blue stars?  Beautiful island universe Messier 94 lies a mere 15 million light-years distant in the northern constellation of the Hunting Dogs (Canes Venatici). A popular target for Earth-based astronomers, the face-on spiral galaxy is about 30,000 light-years across, with spiral arms sweeping through the outskirts of its broad disk. But this Hubble Space Telescope field of view spans about 7,000 light-years across M94's central region. The featured close-up highlights the galaxy's compact, bright nucleus, prominent inner dust lanes, and the remarkable bluish ring of young massive stars. The ring stars are all likely less than 10 million years old, indicating that M94 is a starburst galaxy that is experiencing an epoch of rapid star formation from inspiraling gas. The circular ripple of blue stars is likely a wave propagating outward, having been triggered by the gravity and rotation of a oval matter distributions. Because M94 is relatively nearby, astronomers can better explore details of its starburst ring. Astrophysicists: Browse 2,000+ codes in the Astrophysics Source Code Library",
                    hdurl = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_1002.jpg",
                    media_type = "image",
                    service_version = "v1",
                    title = "Starburst Galaxy M94 from Hubble",
                    url = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg"
                ),
                NasaImageResponseItem(
                    copyright = "Steve Mazlin",
                    date = "2019-12-03",
                    explanation = "Why does this galaxy have a ring of bright blue stars?  Beautiful island universe Messier 94 lies a mere 15 million light-years distant in the northern constellation of the Hunting Dogs (Canes Venatici). A popular target for Earth-based astronomers, the face-on spiral galaxy is about 30,000 light-years across, with spiral arms sweeping through the outskirts of its broad disk. But this Hubble Space Telescope field of view spans about 7,000 light-years across M94's central region. The featured close-up highlights the galaxy's compact, bright nucleus, prominent inner dust lanes, and the remarkable bluish ring of young massive stars. The ring stars are all likely less than 10 million years old, indicating that M94 is a starburst galaxy that is experiencing an epoch of rapid star formation from inspiraling gas. The circular ripple of blue stars is likely a wave propagating outward, having been triggered by the gravity and rotation of a oval matter distributions. Because M94 is relatively nearby, astronomers can better explore details of its starburst ring. Astrophysicists: Browse 2,000+ codes in the Astrophysics Source Code Library",
                    hdurl = "https://apod.nasa.gov/apod/image/1912/M27_Mazlin_2000.jpg",
                    media_type = "image",
                    service_version = "v1",
                    title = "M27: The Dumbbell Nebula",
                    url = "https://apod.nasa.gov/apod/image/1912/M27_Mazlin_960.jpg"
                )
            )
        )
        return mockNasaImageList
    }
}