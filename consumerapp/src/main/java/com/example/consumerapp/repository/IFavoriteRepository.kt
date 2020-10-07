package com.example.consumerapp.repository

import android.database.Cursor

interface IFavoriteRepository {

    suspend fun getAllFavorite(): Cursor?
}