package com.example.dicodingsubmissiontwo.repository.favorite

import androidx.lifecycle.LiveData
import com.example.dicodingsubmissiontwo.db.entity.FavoriteUser

interface IFavoriteRepository {

    suspend fun getAllFavorite(): LiveData<List<FavoriteUser>>
    suspend fun getFavoriteDetailById(userId: Long): FavoriteUser
    suspend fun insertToFavorite(user: FavoriteUser)
    suspend fun removeFromFavorite(user: FavoriteUser)
}