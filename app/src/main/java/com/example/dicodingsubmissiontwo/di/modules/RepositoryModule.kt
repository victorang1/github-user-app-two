package com.example.dicodingsubmissiontwo.di.modules

import com.example.dicodingsubmissiontwo.db.AppDatabase
import com.example.dicodingsubmissiontwo.repository.favorite.FavoriteRepository
import com.example.dicodingsubmissiontwo.repository.favorite.IFavoriteRepository
import com.example.dicodingsubmissiontwo.repository.user.IUserRepository
import com.example.dicodingsubmissiontwo.repository.user.UserRepository
import com.example.dicodingsubmissiontwo.service.UserService
import org.koin.dsl.module

val repositoryModule = module {

    fun provideUserRepositoryModule(userService: UserService): IUserRepository {
        return UserRepository(userService)
    }

    fun provideFavoriteRepositoryModule(appDatabase: AppDatabase) : IFavoriteRepository {
        return FavoriteRepository(appDatabase)
    }

    single { provideUserRepositoryModule(get()) }
    single { provideFavoriteRepositoryModule(get()) }
}