package com.example.obviouscompanyassignment.nasalist.repository

import androidx.lifecycle.LiveData
import com.example.obviouscompanyassignment.base.network.DataResult
import com.example.obviouscompanyassignment.nasalist.data.apimodel.NasaImageResponseItem

interface NasaImageRepository {
    suspend fun fetchNasaImageData()
    val nasaImageDataList: LiveData<DataResult<List<NasaImageResponseItem>>>
}