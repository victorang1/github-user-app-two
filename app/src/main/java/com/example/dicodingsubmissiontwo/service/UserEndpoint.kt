package com.example.dicodingsubmissiontwo.service

import com.example.dicodingsubmissiontwo.service.datamodel.UserSearchItemResponse
import com.example.dicodingsubmissiontwo.service.datamodel.UserSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserEndpoint {

    @GET("/search/users")
    fun getUserByUsername(@Query("q") username: String) : Call<UserSearchResponse>

    @GET("/users/{username}")
    fun getUserDetail(@Path("username") username: String) : Call<UserSearchItemResponse>
}