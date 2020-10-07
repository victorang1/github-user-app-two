package com.example.consumerapp.app

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.consumerapp.model.FavoriteUser
import com.example.consumerapp.repository.IFavoriteRepository
import com.example.consumerapp.util.MappingUtil
import kotlinx.coroutines.launch

class MainViewModel(private val favoriteRepository: IFavoriteRepository) : ViewModel() {

    private var favoriteItems = MutableLiveData<List<FavoriteUser>>()
    private var state = MutableLiveData<Int>()
    private var loadingStatus = MutableLiveData<Boolean>()

    fun getState(): LiveData<Int> = state
    fun getFavoriteItems(): LiveData<List<FavoriteUser>> = favoriteItems
    fun getLoadingStatus(): LiveData<Boolean> = loadingStatus

    companion object {
        const val SHOW_DATA = 1
    }

    fun loadFavoriteItems() {
        loadingStatus.value = true
        viewModelScope.launch {
            val cursor = favoriteRepository.getAllFavorite()
            val items = MappingUtil.mapCursorToArrayList(cursor)
            favoriteItems.value = items
            state.value = SHOW_DATA
            loadingStatus.value = false
        }
    }
}