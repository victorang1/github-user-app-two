package com.example.dicodingsubmissiontwo.di

import com.example.dicodingsubmissiontwo.di.modules.appModule
import org.koin.core.module.Module

val appComponent: List<Module> = listOf(
    appModule
)