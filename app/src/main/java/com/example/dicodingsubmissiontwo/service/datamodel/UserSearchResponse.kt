package com.example.dicodingsubmissiontwo.service.datamodel

import com.google.gson.annotations.SerializedName

data class UserSearchResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    val items: List<UserSearchItemResponse>
)