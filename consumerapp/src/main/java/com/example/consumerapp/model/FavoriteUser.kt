package com.example.consumerapp.model

import androidx.databinding.BaseObservable

class FavoriteUser(
    val userId: Long,
    val username: String,
    val avatar: String,
    val reposCount: String,
    val followers: String,
    val following: String,
) : BaseObservable()