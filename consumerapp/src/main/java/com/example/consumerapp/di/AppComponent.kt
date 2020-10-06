package com.example.consumerapp.di

import com.example.consumerapp.di.modules.appModule
import com.example.dicodingsubmissiontwo.di.modules.repositoryModule
import com.example.dicodingsubmissiontwo.di.modules.viewModelModule
import org.koin.core.module.Module

val appComponent: List<Module> = listOf(
    appModule,
    repositoryModule,
    viewModelModule
)