package com.example.dicodingsubmissiontwo

import android.app.Application
import com.example.dicodingsubmissiontwo.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(appComponent)
        }
    }
}