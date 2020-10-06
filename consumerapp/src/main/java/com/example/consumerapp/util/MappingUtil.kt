package com.example.consumerapp.util

import android.database.Cursor
import com.example.consumerapp.model.FavoriteUser
import com.example.core.FavoriteProviderConfig.Companion.AVATAR
import com.example.core.FavoriteProviderConfig.Companion.FOLLOWERS
import com.example.core.FavoriteProviderConfig.Companion.FOLLOWING
import com.example.core.FavoriteProviderConfig.Companion.REPOS_COUNT
import com.example.core.FavoriteProviderConfig.Companion.USERNAME
import com.example.core.FavoriteProviderConfig.Companion.USER_ID
import java.util.ArrayList

object MappingUtil {

    fun mapCursorToArrayList(notesCursor: Cursor?): ArrayList<FavoriteUser> {
        val notesList = ArrayList<FavoriteUser>()

        notesCursor?.apply {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(USER_ID))
                val username = getString(getColumnIndexOrThrow(USERNAME))
                val avatar = getString(getColumnIndexOrThrow(AVATAR))
                val reposCount = getString(getColumnIndexOrThrow(REPOS_COUNT))
                val followers = getString(getColumnIndexOrThrow(FOLLOWERS))
                val following = getString(getColumnIndexOrThrow(FOLLOWING))
                notesList.add(FavoriteUser(id, username, avatar, reposCount, followers, following))
            }
        }
        return notesList
    }
}