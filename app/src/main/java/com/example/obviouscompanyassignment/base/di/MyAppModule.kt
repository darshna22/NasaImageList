package com.example.obviouscompanyassignment.base.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MyAppModule(private val app: Application) {
    @Provides
    @Singleton
    fun provideContext(): Context = app
}