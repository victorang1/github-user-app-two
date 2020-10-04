package com.example.dicodingsubmissiontwo.model

import android.os.Parcelable
import androidx.databinding.BaseObservable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUser(
    val id: Long,
    val username: String,
    val avatar: String,
    val reposCount: Long,
    val followers: Long,
    val following: Long
) : BaseObservable(), Parcelable {
    constructor(
        id: Long,
        username: String,
        avatar: String
    ) : this(id, username, avatar, 0, 0, 0)
}