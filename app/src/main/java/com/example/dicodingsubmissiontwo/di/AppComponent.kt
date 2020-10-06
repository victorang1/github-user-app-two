package com.example.dicodingsubmissiontwo.di

import com.example.dicodingsubmissiontwo.di.modules.apiModule
import com.example.dicodingsubmissiontwo.di.modules.appModule
import com.example.dicodingsubmissiontwo.di.modules.repositoryModule
import com.example.dicodingsubmissiontwo.di.modules.viewModelModule
import org.koin.core.module.Module

val appComponent: List<Module> = listOf(
    apiModule,
    appModule,
    repositoryModule,
    viewModelModule
)