package com.example.dicodingsubmissiontwo.di.modules

import android.content.Context
import android.content.res.Resources
import com.example.dicodingsubmissiontwo.db.AppDatabase
import com.example.dicodingsubmissiontwo.pref.AppPreferences
import com.example.dicodingsubmissiontwo.repository.user.IUserRepository
import com.example.dicodingsubmissiontwo.repository.user.UserRepository
import com.example.dicodingsubmissiontwo.service.ApiConfig
import com.example.dicodingsubmissiontwo.service.UserService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiConfig.BASE_URL)
            .build()
    }

    fun provideResources(context: Context): Resources {
        return context.resources
    }

    fun provideDatabase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    fun provideAppPreferences(context: Context): AppPreferences {
        return AppPreferences(context)
    }

    single { provideRetrofit() }
    single { provideDatabase(androidContext()) }
    single { provideResources(androidContext()) }
    factory { provideAppPreferences(androidContext()) }
}