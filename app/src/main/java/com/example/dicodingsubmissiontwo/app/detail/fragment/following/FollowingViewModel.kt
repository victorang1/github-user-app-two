package com.example.dicodingsubmissiontwo.app.detail.fragment.following

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingsubmissiontwo.model.GithubUser
import com.example.dicodingsubmissiontwo.repository.user.IUserRepository
import com.example.dicodingsubmissiontwo.service.ApiConfig
import com.example.dicodingsubmissiontwo.service.datamodel.UserSearchItemResponse

class FollowingViewModel(private val userRepository: IUserRepository) : ViewModel() {

    private val errorMessage = MutableLiveData<String>()
    private val userFollowing = MutableLiveData<List<GithubUser>>()
    private val state = MutableLiveData<Int>()

    fun getErrorMessage(): LiveData<String> = errorMessage
    fun getUserFollowing(): LiveData<List<GithubUser>> = userFollowing
    fun getState(): LiveData<Int> = state

    fun loadFollowers(githubUser: GithubUser) {
        userRepository.fetchUserFollowing(
            githubUser.username,
            object : ApiConfig.ApiHandler<List<UserSearchItemResponse>> {
                override fun onSuccess(response: List<UserSearchItemResponse>) {
                    val list = mutableListOf<GithubUser>()
                    if (!response.isNullOrEmpty()) {
                        for (itemResponse in response) {
                            list.add(setData(itemResponse))
                        }
                    }
                    userFollowing.value = list
                    state.value = ApiConfig.REQUEST_SUCCESS
                }

                override fun onFailure(throwable: Throwable) {
                    errorMessage.value = throwable.message
                    state.value = ApiConfig.REQUEST_ERROR
                }
            })
    }

    private fun setData(itemResponse: UserSearchItemResponse): GithubUser {
        return GithubUser(itemResponse.id, itemResponse.username, itemResponse.avatar)
    }
}