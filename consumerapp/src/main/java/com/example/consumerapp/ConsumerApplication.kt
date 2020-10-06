package com.example.consumerapp

import android.app.Application
import com.example.consumerapp.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ConsumerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ConsumerApplication)
            modules(appComponent)
        }
    }
}