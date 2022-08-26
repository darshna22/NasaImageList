package com.example.obviouscompanyassignment.nasalist.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.obviouscompanyassignment.base.network.DataResult
import com.example.obviouscompanyassignment.nasalist.data.ApiService
import com.example.obviouscompanyassignment.nasalist.data.apimodel.NasaImageResponseItem
import javax.inject.Inject

class NasaImageRepositoryImpl
@Inject constructor(private val retrofitService: ApiService) : NasaImageRepository {

    private val nasaImageResponse = MutableLiveData<DataResult<List<NasaImageResponseItem>>>()

    override suspend fun fetchNasaImageData() {
        try {

            nasaImageResponse.value = DataResult.Loading
            val call = retrofitService.getNasaImageList()
            nasaImageResponse.value = DataResult.Success(call.body()!!)
        } catch (e: Exception) {
            nasaImageResponse.value = DataResult.Error(e.message)

        }
    }

    override val nasaImageDataList: LiveData<DataResult<List<NasaImageResponseItem>>>
        get() = nasaImageResponse


}