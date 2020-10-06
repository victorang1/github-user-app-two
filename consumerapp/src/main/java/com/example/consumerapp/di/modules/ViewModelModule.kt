package com.example.dicodingsubmissiontwo.di.modules

import com.example.consumerapp.app.MainViewModel
import com.example.consumerapp.repository.IFavoriteRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    fun provideMainViewModel(favoriteRepository: IFavoriteRepository): MainViewModel {
        return MainViewModel(favoriteRepository)
    }

    viewModel { provideMainViewModel(get()) }
}