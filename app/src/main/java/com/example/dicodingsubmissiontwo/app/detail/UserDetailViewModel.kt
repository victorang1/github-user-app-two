package com.example.dicodingsubmissiontwo.app.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dicodingsubmissiontwo.db.entity.FavoriteUser
import com.example.dicodingsubmissiontwo.model.GithubUser
import com.example.dicodingsubmissiontwo.repository.favorite.IFavoriteRepository
import com.example.dicodingsubmissiontwo.repository.user.IUserRepository
import com.example.dicodingsubmissiontwo.service.ApiConfig
import com.example.dicodingsubmissiontwo.service.datamodel.UserSearchItemResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UserDetailViewModel(
    private val userRepository: IUserRepository,
    private val favoriteRepository: IFavoriteRepository
) : ViewModel() {

    companion object {
        const val ACTION_SUCCESS = 1
    }

    private val errorMessage = MutableLiveData<String>()
    private val userData = MutableLiveData<GithubUser>()
    private val isLoading = MutableLiveData<Boolean>()
    private val isFavorite = MutableLiveData<Boolean>()
    private val state = MutableLiveData<Int>()

    fun getErrorMessage(): LiveData<String> = errorMessage
    fun getUserData(): LiveData<GithubUser> = userData
    fun getLoadingStatus(): LiveData<Boolean> = isLoading
    fun getFavoriteStatus(): LiveData<Boolean> = isFavorite
    fun getState(): LiveData<Int> = state

    fun getUserData(githubUser: GithubUser) {
        isLoading.value = true
        userRepository.getUserDetails(
            githubUser.username,
            object : ApiConfig.ApiHandler<UserSearchItemResponse> {
                override fun onSuccess(response: UserSearchItemResponse) {
                    userData.value = if (response.equals(null)) null else setData(response)
                    isLoading.value = false
                    state.value = ApiConfig.REQUEST_SUCCESS
                }

                override fun onFailure(throwable: Throwable) {
                    errorMessage.value = throwable.message
                    isLoading.value = false
                    state.value = ApiConfig.REQUEST_ERROR
                }
            })
    }

    private fun setData(itemResponse: UserSearchItemResponse): GithubUser {
        with (itemResponse) {
            return GithubUser(
                id,
                username,
                avatar,
                reposCount,
                followers,
                following,
                location,
                company,
                name
            )
        }
    }

    fun checkFavoriteFromStorage(user: GithubUser) {
        viewModelScope.launch {
            val isExists = async {
                favoriteRepository.getFavoriteDetailById(user.id)
            }
            isFavorite.value = isExists.await() != null
        }
    }

    fun runFavoriteAction(user: GithubUser) {
        viewModelScope.launch {
            val favoriteUser = convertToFavoriteUser(user)
            if (getFavoriteStatus().value == true) {
                favoriteRepository.removeFromFavorite(favoriteUser)
            } else {
                favoriteRepository.insertToFavorite(favoriteUser)
            }
            state.value = ACTION_SUCCESS
        }
    }

    private fun convertToFavoriteUser(user: GithubUser): FavoriteUser {
        return FavoriteUser(
            user.id,
            user.username,
            user.avatar,
            user.reposCount.toString(),
            user.followers.toString(),
            user.following.toString()
        )
    }
}