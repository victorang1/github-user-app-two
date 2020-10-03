package com.example.dicodingsubmissiontwo.di.modules

import com.example.dicodingsubmissiontwo.service.UserEndpoint
import com.example.dicodingsubmissiontwo.service.UserService
import com.example.dicodingsubmissiontwo.service.UserServiceImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun provideUserEndpoint(retrofit: Retrofit) : UserEndpoint {
        return retrofit.create(UserEndpoint::class.java)
    }

    fun provideUserService(userEndpoint: UserEndpoint) : UserService {
        return UserServiceImpl(userEndpoint)
    }

    factory { provideUserEndpoint(get()) }
    factory { provideUserService(get()) }
}