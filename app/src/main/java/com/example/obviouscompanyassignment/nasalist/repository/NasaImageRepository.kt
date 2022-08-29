package com.example.obviouscompanyassignment.nasalist.repository

import com.example.obviouscompanyassignment.nasalist.data.apimodel.NasaImageResponseItem

interface NasaImageRepository {
    suspend fun fetchNasaImageData():List<NasaImageResponseItem>
}