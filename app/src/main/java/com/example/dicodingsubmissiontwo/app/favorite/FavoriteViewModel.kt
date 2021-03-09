package com.example.dicodingsubmissiontwo.app.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dicodingsubmissiontwo.db.entity.FavoriteUser
import com.example.dicodingsubmissiontwo.model.GithubUser
import com.example.dicodingsubmissiontwo.repository.favorite.IFavoriteRepository
import kotlinx.coroutines.*

class FavoriteViewModel(private val favoriteRepository: IFavoriteRepository) : ViewModel() {

    companion object {
        const val DELETE_SUCCESS = 1
    }

    private val state = MutableLiveData<Int>()
    fun getState(): LiveData<Int> = state

    fun getFavoriteItems(): LiveData<List<FavoriteUser>> = runBlocking {
        favoriteRepository.getAllFavorite()
    }

    fun getGithubUserObject(favoriteUser: FavoriteUser): GithubUser {
        return GithubUser(
            favoriteUser.userId,
            favoriteUser.username,
            favoriteUser.avatar,
            favoriteUser.reposCount.toLong(),
            favoriteUser.followers.toLong(),
            favoriteUser.following.toLong(),
            "", "", ""
        )
    }

    fun removeFavoriteUser(favoriteUser: FavoriteUser) {
        viewModelScope.launch {
            favoriteRepository.removeFromFavorite(favoriteUser)
            state.value = DELETE_SUCCESS
        }
    }
}