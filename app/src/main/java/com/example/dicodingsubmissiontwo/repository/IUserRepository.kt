package com.example.dicodingsubmissiontwo.repository

import com.example.dicodingsubmissiontwo.service.ApiConfig
import com.example.dicodingsubmissiontwo.service.datamodel.UserSearchItemResponse

interface IUserRepository {

    fun getUserByUsername(username: String, callback: ApiConfig.ApiHandler<List<UserSearchItemResponse>>)
}