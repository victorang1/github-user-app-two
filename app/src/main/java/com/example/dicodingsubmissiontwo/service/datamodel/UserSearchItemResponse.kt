package com.example.dicodingsubmissiontwo.service.datamodel

import com.google.gson.annotations.SerializedName

data class UserSearchItemResponse(
    @SerializedName("login")
    val username: String,
    val id: Long,
    @SerializedName("avatar_url")
    val avatar: String
)