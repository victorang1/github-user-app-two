package com.example.dicodingsubmissiontwo.di.modules

import com.example.dicodingsubmissiontwo.service.ApiConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiConfig.BASE_URL)
            .build()
    }

    single { provideRetrofit() }
}