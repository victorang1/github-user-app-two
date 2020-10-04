package com.example.dicodingsubmissiontwo.service

import com.example.dicodingsubmissiontwo.BuildConfig
import com.example.dicodingsubmissiontwo.service.datamodel.UserSearchItemResponse
import com.example.dicodingsubmissiontwo.service.datamodel.UserSearchResponse
import retrofit2.Call
import retrofit2.http.*

interface UserEndpoint {

    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("/search/users")
    fun getUserByUsername(@Query("q") username: String) : Call<UserSearchResponse>

    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("/users/{username}")
    fun getUserDetail(@Path("username") username: String) : Call<UserSearchItemResponse>

    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("/users/{username}/followers")
    fun getUserFollowers(@Path("username") username: String) : Call<List<UserSearchItemResponse>>

    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("/users/{username}/following")
    fun getUserFollowing(@Path("username") username: String): Call<List<UserSearchItemResponse>>
}