package com.example.consumerapp.repository

import android.content.ContentResolver
import android.database.Cursor
import com.example.core.FavoriteProviderConfig.Companion.CONTENT_URI
import org.koin.core.KoinComponent

class FavoriteRepository(private val contentResolver: ContentResolver) : IFavoriteRepository, KoinComponent {

    override suspend fun getAllFavorite(): Cursor? {
        return contentResolver.query(CONTENT_URI, null, null, null, null)
    }
}