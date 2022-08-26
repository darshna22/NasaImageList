package com.example.obviouscompanyassignment.base.di

import com.example.obviouscompanyassignment.nasalist.repository.NasaImageRepository
import com.example.obviouscompanyassignment.nasalist.repository.NasaImageRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class NasaImageDataModule {
    @Binds
    abstract fun provideNasaImageDataViewModel(nasaImageRepositoryImpl: NasaImageRepositoryImpl): NasaImageRepository

}