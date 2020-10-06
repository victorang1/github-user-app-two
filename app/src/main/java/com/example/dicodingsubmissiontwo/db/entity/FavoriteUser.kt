package com.example.dicodingsubmissiontwo.db.entity

import androidx.databinding.BaseObservable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
class FavoriteUser(
    @PrimaryKey
    val userId: Long,
    val username: String,
    val avatar: String,
    val reposCount: Long,
    val followers: Long,
    val following: Long,
) : BaseObservable()