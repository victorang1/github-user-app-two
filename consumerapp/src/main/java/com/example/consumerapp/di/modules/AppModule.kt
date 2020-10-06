package com.example.consumerapp.di.modules

import android.content.ContentResolver
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    fun provideContentResolver(context: Context) : ContentResolver {
        return context.contentResolver
    }

    factory { provideContentResolver(androidContext()) }
}