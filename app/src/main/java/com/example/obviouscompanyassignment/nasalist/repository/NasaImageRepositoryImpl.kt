package com.example.obviouscompanyassignment.nasalist.repository

import com.example.obviouscompanyassignment.nasalist.data.ApiService
import com.example.obviouscompanyassignment.nasalist.data.apimodel.NasaImageResponseItem
import javax.inject.Inject

class NasaImageRepositoryImpl
@Inject constructor(private val retrofitService: ApiService) : NasaImageRepository {

    override suspend fun fetchNasaImageData(): List<NasaImageResponseItem> {
        val call = retrofitService.getNasaImageList()
        return call.body()!!
    }

}