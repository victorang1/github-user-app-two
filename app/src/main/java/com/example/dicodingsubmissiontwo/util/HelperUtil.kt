package com.example.dicodingsubmissiontwo.util

import android.content.ContentValues
import com.example.dicodingsubmissiontwo.db.entity.FavoriteUser
import com.example.dicodingsubmissiontwo.provider.FavoriteProviderConfig.Companion.AVATAR
import com.example.dicodingsubmissiontwo.provider.FavoriteProviderConfig.Companion.FOLLOWERS
import com.example.dicodingsubmissiontwo.provider.FavoriteProviderConfig.Companion.FOLLOWING
import com.example.dicodingsubmissiontwo.provider.FavoriteProviderConfig.Companion.REPOS_COUNT
import com.example.dicodingsubmissiontwo.provider.FavoriteProviderConfig.Companion.USERNAME
import com.example.dicodingsubmissiontwo.provider.FavoriteProviderConfig.Companion.USER_ID

class HelperUtil {

    companion object {

        fun fromContentValuesToFavoriteUser(contentValues: ContentValues?): FavoriteUser {
            val userId = contentValues?.getAsLong(USER_ID) ?: -1
            val username = contentValues?.getAsString(USERNAME) ?: ""
            val avatar = contentValues?.getAsString(AVATAR) ?: ""
            val reposCount = contentValues?.getAsString(REPOS_COUNT) ?: ""
            val followers = contentValues?.getAsString(FOLLOWERS) ?: ""
            val following = contentValues?.getAsString(FOLLOWING) ?: ""
            return FavoriteUser(userId, username, avatar, reposCount, followers, following)
        }
    }
}