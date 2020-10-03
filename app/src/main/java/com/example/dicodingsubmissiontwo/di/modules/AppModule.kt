package com.example.dicodingsubmissiontwo.di.modules

import android.content.Context
import android.content.res.Resources
import com.example.dicodingsubmissiontwo.repository.IUserRepository
import com.example.dicodingsubmissiontwo.repository.UserRepository
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

    fun provideResources(context: Context) : Resources {
        return context.resources
    }

    fun provideRepositoryModule(userService: UserService): IUserRepository {
        return UserRepository(userService)
    }

    single { provideRetrofit() }
    single { provideRepositoryModule(get()) }
    single { provideResources(androidContext()) }
}