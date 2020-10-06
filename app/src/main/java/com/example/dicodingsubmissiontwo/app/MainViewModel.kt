package com.example.dicodingsubmissiontwo.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingsubmissiontwo.model.GithubUser
import com.example.dicodingsubmissiontwo.repository.user.IUserRepository
import com.example.dicodingsubmissiontwo.service.ApiConfig
import com.example.dicodingsubmissiontwo.service.ApiConfig.Companion.REQUEST_ERROR
import com.example.dicodingsubmissiontwo.service.ApiConfig.Companion.REQUEST_SUCCESS
import com.example.dicodingsubmissiontwo.service.datamodel.UserSearchItemResponse

class MainViewModel(private val userRepository: IUserRepository) : ViewModel() {

    private val errorMessage = MutableLiveData<String>()
    private val searchResult = MutableLiveData<List<GithubUser>>()
    private val isLoading = MutableLiveData<Boolean>()
    private val state = MutableLiveData<Int>()

    fun getErrorMessage(): LiveData<String> = errorMessage
    fun getSearchResult(): LiveData<List<GithubUser>> = searchResult
    fun getLoadingStatus(): LiveData<Boolean> = isLoading
    fun getState(): LiveData<Int> = state

    fun searchUser(username: String) {
        isLoading.value = true
        userRepository.getUserByUsername(
            username,
            object : ApiConfig.ApiHandler<List<UserSearchItemResponse>> {
                override fun onSuccess(response: List<UserSearchItemResponse>) {
                    val list = mutableListOf<GithubUser>()
                    if (!response.isNullOrEmpty()) {
                        for (itemResponse in response) {
                            list.add(setData(itemResponse))
                        }
                    }
                    searchResult.value = list
                    isLoading.value = false
                    state.value = REQUEST_SUCCESS
                }

                override fun onFailure(throwable: Throwable) {
                    errorMessage.value = throwable.message
                    isLoading.value = false
                    state.value = REQUEST_ERROR
                }
            })
    }

    private fun setData(itemResponse: UserSearchItemResponse): GithubUser {
        return GithubUser(itemResponse.id, itemResponse.username, itemResponse.avatar)
    }
}