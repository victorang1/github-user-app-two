package com.example.dicodingsubmissiontwo.di.modules

import com.example.dicodingsubmissiontwo.app.MainViewModel
import com.example.dicodingsubmissiontwo.repository.IUserRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    fun provideMainViewModel(userRepository: IUserRepository): MainViewModel {
        return MainViewModel(userRepository)
    }

    viewModel { provideMainViewModel(get()) }
}