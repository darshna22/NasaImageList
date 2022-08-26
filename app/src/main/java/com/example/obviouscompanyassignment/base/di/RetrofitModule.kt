package com.example.obviouscompanyassignment.base.di

import com.example.obviouscompanyassignment.base.network.RetrofitBuilder
import com.example.obviouscompanyassignment.nasalist.data.ApiService
import dagger.Module
import dagger.Provides

@Module
class RetrofitModule {

    @Provides
    fun provideRetrofitService(retrofitBuilder: RetrofitBuilder): ApiService =
        retrofitBuilder.buildAPI(ApiService::class.java)
}