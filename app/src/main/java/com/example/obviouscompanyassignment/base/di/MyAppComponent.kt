package com.example.obviouscompanyassignment.base.di

import com.example.obviouscompanyassignment.nasalist.repository.NasaImageRepository
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [MyAppModule::class, NasaImageDataModule::class, RetrofitModule::class])
interface MyAppComponent {
    val nasaImageRepository: NasaImageRepository
}

