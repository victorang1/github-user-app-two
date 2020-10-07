package com.example.core

import android.net.Uri

class FavoriteProviderConfig {

    companion object {
        const val AUTHORITY = "com.example.victor.github"
        const val SCHEME = "content"
        const val TABLE_NAME = "note"
        val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME)
            .build()

        const val USER_ID = "userId"
        const val USERNAME = "username"
        const val AVATAR = "avatar"
        const val REPOS_COUNT = "reposCount"
        const val FOLLOWERS = "followers"
        const val FOLLOWING = "following"
    }
}