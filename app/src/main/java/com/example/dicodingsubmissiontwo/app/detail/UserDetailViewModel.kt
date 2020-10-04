package com.example.dicodingsubmissiontwo.app.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingsubmissiontwo.model.GithubUser
import com.example.dicodingsubmissiontwo.repository.IUserRepository
import com.example.dicodingsubmissiontwo.service.ApiConfig
import com.example.dicodingsubmissiontwo.service.datamodel.UserSearchItemResponse

class UserDetailViewModel(private val userRepository: IUserRepository) : ViewModel() {

    private val errorMessage = MutableLiveData<String>()
    private val userData = MutableLiveData<GithubUser>()
    private val isLoading = MutableLiveData<Boolean>()
    private val state = MutableLiveData<Int>()

    fun getErrorMessage(): LiveData<String> = errorMessage
    fun getUserData(): LiveData<GithubUser> = userData
    fun getLoadingStatus(): LiveData<Boolean> = isLoading
    fun getState(): LiveData<Int> = state

    fun getUserData(githubUser: GithubUser) {
        isLoading.value = true
        userRepository.getUserDetails(
            githubUser.username,
            object : ApiConfig.ApiHandler<UserSearchItemResponse> {
                override fun onSuccess(response: UserSearchItemResponse) {
                    userData.value = if(response.equals(null)) null else setData(response)
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
        return GithubUser(
            itemResponse.id,
            itemResponse.username,
            itemResponse.avatar,
            itemResponse.reposCount,
            itemResponse.followers,
            itemResponse.following,
            itemResponse.location,
            itemResponse.company,
            itemResponse.name
        )
    }
}