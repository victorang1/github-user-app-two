package com.example.dicodingsubmissiontwo.service

import com.example.dicodingsubmissiontwo.service.datamodel.UserSearchItemResponse
import com.example.dicodingsubmissiontwo.service.datamodel.UserSearchResponse
import retrofit2.Call

class UserServiceImpl(private val userEndpoint: UserEndpoint) : UserService {

    override fun getUserByUsername(username: String): Call<UserSearchResponse> {
        return userEndpoint.getUserByUsername(username)
    }

    override fun getUserDetail(username: String): Call<UserSearchItemResponse> {
        return userEndpoint.getUserDetail(username)
    }

    override fun getUserFollowers(username: String): Call<List<UserSearchItemResponse>> {
        return userEndpoint.getUserFollowers(username)
    }

    override fun getUserFollowing(username: String): Call<List<UserSearchItemResponse>> {
        return userEndpoint.getUserFollowing(username)
    }
}