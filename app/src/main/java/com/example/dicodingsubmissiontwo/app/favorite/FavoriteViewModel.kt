package com.example.dicodingsubmissiontwo.app.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingsubmissiontwo.db.entity.FavoriteUser
import com.example.dicodingsubmissiontwo.repository.favorite.IFavoriteRepository
import kotlinx.coroutines.*

class FavoriteViewModel(private val favoriteRepository: IFavoriteRepository): ViewModel() {

    fun getFavoriteItems(): LiveData<List<FavoriteUser>> = runBlocking {
        favoriteRepository.getAllFavorite()
    }
}