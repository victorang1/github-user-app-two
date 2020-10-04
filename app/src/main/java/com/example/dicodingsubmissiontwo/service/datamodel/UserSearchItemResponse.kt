package com.example.dicodingsubmissiontwo.service.datamodel

import com.google.gson.annotations.SerializedName

data class UserSearchItemResponse(
    @SerializedName("login")
    val username: String,
    val id: Long,
    @SerializedName("avatar_url")
    val avatar: String,
    @SerializedName("public_repos")
    val reposCount: Long,
    val followers: Long,
    val following: Long,
    val location: String,
    val company: String,
    val name: String
)