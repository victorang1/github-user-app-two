package com.example.dicodingsubmissiontwo.model

import android.content.res.Resources
import android.os.Parcelable
import android.view.View
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
    val following: Long,
    val location: String?,
    val company: String?,
    val name: String?
) : BaseObservable(), Parcelable, KoinComponent {
    @IgnoredOnParcel
    private val resources: Resources by inject()

    constructor() : this(0, "", "", 0, 0, 0, "", "", "")

    constructor(
        id: Long,
        username: String,
        avatar: String
    ) : this(id, username, avatar, 0, 0, 0, "", "", "")

    @Bindable
    fun getRepositoryDisplay(): String =
        String.format(resources.getString(R.string.text_repository_value), reposCount)

    @Bindable
    fun getFollowerDisplay(): String =
        String.format(resources.getString(R.string.text_follower_value), followers)


    @Bindable
    fun getFollowingDisplay(): String =
        String.format(resources.getString(R.string.text_following_value), following)

    @Bindable
    fun getNameDisplay(): String? {
        if (name.isNullOrEmpty()) return "-"
        return name
    }


    @Bindable
    fun getLocationDisplay(): String {
        if (location.isNullOrEmpty()) return "-"
        return location
    }


    @Bindable
    fun getCompanyDisplay(): String {
        if (company.isNullOrEmpty()) return "-"
        return company
    }

    @Bindable
    fun getNameVisibility(): Int {
        return if (name.isNullOrEmpty()) View.GONE else View.VISIBLE
    }

    @Bindable
    fun getCompanyVisibility(): Int {
        return if (company.isNullOrEmpty()) View.GONE else View.VISIBLE
    }

    @Bindable
    fun getLocationVisibility(): Int {
        return if (location.isNullOrEmpty()) View.GONE else View.VISIBLE
    }
}