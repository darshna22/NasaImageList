package com.example.obviouscompanyassignment.base.di.activity

import android.app.Activity
import com.example.obviouscompanyassignment.base.di.MyAppComponent
import com.example.obviouscompanyassignment.nasaimagedetail.view.NasaDetailActivity
import com.example.obviouscompanyassignment.nasalist.view.NasaGridActivity
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class], dependencies = [MyAppComponent::class])
interface ActivityComponent {
    fun inject(nasaGridActivity: NasaGridActivity)
    fun inject(nasaDetailActivity: NasaDetailActivity)
    val activity: Activity
}

