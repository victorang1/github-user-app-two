package com.example.dicodingsubmissiontwo.model

import android.content.res.Resources
import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.dicodingsubmissiontwo.R
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import org.koin.core.KoinComponent
import org.koin.core.inject

@Parcelize
data class GithubUser(
    val id: Long,
    val username: String,
    val avatar: String,
    val reposCount: Long,
    val followers: Long,
    val following: Long
) : BaseObservable(), Parcelable, KoinComponent {
    @IgnoredOnParcel
    private val resources: Resources by inject()

    constructor(
        id: Long,
        username: String,
        avatar: String
    ) : this(id, username, avatar, 0, 0, 0)

    @Bindable
    fun getRepositoryDisplay(): String {
        return String.format(resources.getString(R.string.text_repository_value), reposCount)
    }

    @Bindable
    fun getFollowerDisplay(): String {
        return String.format(resources.getString(R.string.text_follower_value), followers)
    }

    @Bindable
    fun getFollowingDisplay(): String {
        return String.format(resources.getString(R.string.text_following_value), following)
    }
}