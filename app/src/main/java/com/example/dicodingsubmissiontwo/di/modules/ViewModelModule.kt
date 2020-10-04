package com.example.dicodingsubmissiontwo.di.modules

import com.example.dicodingsubmissiontwo.app.MainViewModel
import com.example.dicodingsubmissiontwo.app.detail.UserDetailViewModel
import com.example.dicodingsubmissiontwo.app.detail.fragment.follower.FollowerViewModel
import com.example.dicodingsubmissiontwo.app.detail.fragment.following.FollowingViewModel
import com.example.dicodingsubmissiontwo.repository.IUserRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    fun provideMainViewModel(userRepository: IUserRepository): MainViewModel {
        return MainViewModel(userRepository)
    }

    fun provideUserDetailViewModel(userRepository: IUserRepository): UserDetailViewModel {
        return UserDetailViewModel(userRepository)
    }

    fun provideFollowerViewModel(userRepository: IUserRepository) : FollowerViewModel {
        return FollowerViewModel(userRepository)
    }

    fun provideFollowingViewModel(userRepository: IUserRepository) : FollowingViewModel {
        return FollowingViewModel(userRepository)
    }

    viewModel { provideMainViewModel(get()) }
    viewModel { provideUserDetailViewModel(get()) }
    viewModel { provideFollowerViewModel(get()) }
    viewModel { provideFollowingViewModel(get()) }
}