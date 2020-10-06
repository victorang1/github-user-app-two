package com.example.dicodingsubmissiontwo.provider

import android.net.Uri

class FavoriteProviderConfig {

    companion object {
        val AUTHORITY = "com.example.victor.github"
        val SCHEME = "content"
        val TABLE_NAME = "note"
        val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME)
            .build()

        val USER_ID = "userId"
        val USERNAME = "username"
        val AVATAR = "avatar"
        val REPOS_COUNT = "reposCount"
        val FOLLOWERS = "followers"
        val FOLLOWING = "following"
    }
}