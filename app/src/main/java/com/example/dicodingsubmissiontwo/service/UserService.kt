package com.example.dicodingsubmissiontwo.service

import com.example.dicodingsubmissiontwo.service.datamodel.UserSearchItemResponse
import com.example.dicodingsubmissiontwo.service.datamodel.UserSearchResponse
import retrofit2.Call

interface UserService {

    fun getUserByUsername(username: String) : Call<UserSearchResponse>

    fun getUserDetail(username: String) : Call<UserSearchItemResponse>
}