package com.example.dicodingsubmissiontwo.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingsubmissiontwo.model.GithubUser
import com.example.dicodingsubmissiontwo.repository.IUserRepository
import com.example.dicodingsubmissiontwo.service.ApiConfig
import com.example.dicodingsubmissiontwo.service.datamodel.UserSearchItemResponse

class MainViewModel(private val userRepository: IUserRepository) : ViewModel() {

    private val errorMessage = MutableLiveData<String>()
    private val searchResult = MutableLiveData<List<GithubUser>>()
    private val isLoading = MutableLiveData<Boolean>()

    fun getErrorMessage(): LiveData<String> = errorMessage
    fun getSearchResult(): LiveData<List<GithubUser>> = searchResult
    fun getLoadingStatus(): LiveData<Boolean> = isLoading

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
                    isLoading.value = false
                    searchResult.value = list
                }

                override fun onFailure(throwable: Throwable) {
                    isLoading.value = false
                    errorMessage.value = throwable.message
                }
            })
    }

    private fun setData(itemResponse: UserSearchItemResponse): GithubUser {
        return GithubUser(itemResponse.id, itemResponse.username, itemResponse.username)
    }
}