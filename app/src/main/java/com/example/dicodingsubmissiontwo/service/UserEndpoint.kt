package com.example.dicodingsubmissiontwo.service

import com.example.dicodingsubmissiontwo.service.datamodel.UserSearchItemResponse
import com.example.dicodingsubmissiontwo.service.datamodel.UserSearchResponse
import retrofit2.Call
import retrofit2.http.*

interface UserEndpoint {

    @Headers("Authorization: token e34764e862e72f4102d372686f8798b03d0a05db")
    @GET("/search/users")
    fun getUserByUsername(@Query("q") username: String) : Call<UserSearchResponse>

    @Headers("Authorization: token e34764e862e72f4102d372686f8798b03d0a05db")
    @GET("/users/{username}")
    fun getUserDetail(@Path("username") username: String) : Call<UserSearchItemResponse>

    @Headers("Authorization: token e34764e862e72f4102d372686f8798b03d0a05db")
    @GET("/users/{username}/followers")
    fun getUserFollowers(@Path("username") username: String) : Call<List<UserSearchItemResponse>>

    @Headers("Authorization: token e34764e862e72f4102d372686f8798b03d0a05db")
    @GET("/users/{username}/following")
    fun getUserFollowing(@Path("username") username: String): Call<List<UserSearchItemResponse>>
}