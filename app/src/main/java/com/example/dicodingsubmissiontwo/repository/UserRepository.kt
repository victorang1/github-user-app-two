package com.example.dicodingsubmissiontwo.repository

import android.content.res.Resources
import com.example.dicodingsubmissiontwo.R
import com.example.dicodingsubmissiontwo.service.ApiConfig
import com.example.dicodingsubmissiontwo.service.UserService
import com.example.dicodingsubmissiontwo.service.datamodel.UserSearchItemResponse
import com.example.dicodingsubmissiontwo.service.datamodel.UserSearchResponse
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class UserRepository(private val userService: UserService) : IUserRepository, KoinComponent {

    private val resources: Resources by inject()

    override fun getUserByUsername(username: String, callback: ApiConfig.ApiHandler<List<UserSearchItemResponse>>) {
        userService.getUserByUsername(username).enqueue(object : Callback<UserSearchResponse> {
            override fun onResponse(
                call: Call<UserSearchResponse>,
                response: Response<UserSearchResponse>
            ) {
                if (response.isSuccessful) {
                    try {
                        callback.onSuccess(response.body()!!.items)
                    } catch (e: Exception) {
                        callback.onFailure(e)
                    }
                }
                else {
                    callback.onFailure(Throwable(resources.getString(R.string.text_search_error)))
                }
            }

            override fun onFailure(call: Call<UserSearchResponse>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }
}