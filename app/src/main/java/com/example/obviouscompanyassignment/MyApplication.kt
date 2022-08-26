package com.example.obviouscompanyassignment

import android.app.Application
import com.example.obviouscompanyassignment.base.di.DaggerMyAppComponent
import com.example.obviouscompanyassignment.base.di.MyAppModule
import com.example.obviouscompanyassignment.base.di.MyAppComponent

class MyApplication : Application() {

    lateinit var appComponent: MyAppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = initDagger(this)
    }

    private fun initDagger(app: MyApplication): MyAppComponent =
        DaggerMyAppComponent.builder()
            .myAppModule(MyAppModule(app))
            .build()
}