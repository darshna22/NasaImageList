package com.example.obviouscompanyassignment.nasalist.data

import com.example.obviouscompanyassignment.nasalist.data.apimodel.NasaImageResponseItem
import retrofit2.Response
import retrofit2.http.GET

public interface ApiService {

    @GET("f271dc90-7bce-43fd-a777-1e65974826ed")
    suspend fun getNasaImageList(): Response<List<NasaImageResponseItem>>

}