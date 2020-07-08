package com.example.koinsample

import android.app.Application
import com.example.koinsample.di.networkModule
import com.example.koinsample.di.viewmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KoinSampleApplicationClass : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KoinSampleApplicationClass)
            androidLogger()
            modules(listOf(viewmodelModule, networkModule))
        }
    }

}