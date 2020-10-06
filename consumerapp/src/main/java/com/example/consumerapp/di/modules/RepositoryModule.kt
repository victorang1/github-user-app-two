package com.example.dicodingsubmissiontwo.di.modules

import android.content.ContentResolver
import com.example.consumerapp.repository.FavoriteRepository
import com.example.consumerapp.repository.IFavoriteRepository
import org.koin.dsl.module

val repositoryModule = module {

    fun provideFavoriteRepository(contentResolver: ContentResolver): IFavoriteRepository {
        return FavoriteRepository(contentResolver)
    }

    single { provideFavoriteRepository(get()) }
}