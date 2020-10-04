package com.example.dicodingsubmissiontwo.di.modules

import com.example.dicodingsubmissiontwo.app.MainViewModel
import com.example.dicodingsubmissiontwo.app.detail.UserDetailViewModel
import com.example.dicodingsubmissiontwo.repository.IUserRepository
import com.example.dicodingsubmissiontwo.repository.UserRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    fun provideMainViewModel(userRepository: IUserRepository): MainViewModel {
        return MainViewModel(userRepository)
    }

    fun provideUserDetailViewModel(userRepository: IUserRepository): UserDetailViewModel {
        return UserDetailViewModel(userRepository)
    }

    viewModel { provideMainViewModel(get()) }
    viewModel { provideUserDetailViewModel(get()) }
}