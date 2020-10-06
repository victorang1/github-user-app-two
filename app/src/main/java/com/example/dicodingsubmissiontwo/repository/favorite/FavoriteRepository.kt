package com.example.dicodingsubmissiontwo.repository.favorite

import androidx.lifecycle.LiveData
import com.example.dicodingsubmissiontwo.db.AppDatabase
import com.example.dicodingsubmissiontwo.db.entity.FavoriteUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent

class FavoriteRepository(db: AppDatabase) : IFavoriteRepository, KoinComponent {

    private val favoriteDao = db.getFavoriteDao()

    override suspend fun getAllFavorite(): LiveData<List<FavoriteUser>> = favoriteDao.getAllFavoriteUser()

    override suspend fun getFavoriteDetailById(userId: Long): FavoriteUser =
        favoriteDao.getFavoriteUserById(userId)

    override suspend fun insertToFavorite(user: FavoriteUser) = withContext(Dispatchers.IO) {
        favoriteDao.insertToFavorite(user)
    }

    override suspend fun removeFromFavorite(user: FavoriteUser) = withContext(Dispatchers.IO) {
        favoriteDao.removeFromFavorite(user)
    }
}