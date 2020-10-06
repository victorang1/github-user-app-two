package com.example.dicodingsubmissiontwo.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.core.FavoriteProviderConfig.Companion.AUTHORITY
import com.example.core.FavoriteProviderConfig.Companion.CONTENT_URI
import com.example.core.FavoriteProviderConfig.Companion.TABLE_NAME
import com.example.dicodingsubmissiontwo.db.AppDatabase
import com.example.dicodingsubmissiontwo.db.dao.FavoriteProviderDao
import com.example.dicodingsubmissiontwo.util.HelperUtil

class FavoriteProvider : ContentProvider() {

    companion object {

        private const val FAVORITE = 1
        private const val FAVORITE_ID = 2
        private lateinit var favoriteProviderDao: FavoriteProviderDao

        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            sUriMatcher.addURI(AUTHORITY, TABLE_NAME, FAVORITE)
            sUriMatcher.addURI(AUTHORITY, "$TABLE_NAME/#", FAVORITE_ID)
        }
    }

    override fun onCreate(): Boolean {
        favoriteProviderDao = AppDatabase.getInstance(context as Context).getFavoriteProviderDao()
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        var cursor: Cursor?
        when (sUriMatcher.match(uri)) {
            FAVORITE -> cursor = favoriteProviderDao.getAllFavoriteUser()
            FAVORITE_ID -> cursor =
                favoriteProviderDao.getFavoriteUserById(uri.lastPathSegment.toString().toLong())
            else -> cursor = null
        }
        return cursor
    }

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val added: Long = when (FAVORITE) {
            sUriMatcher.match(uri) -> {
                val value = HelperUtil.fromContentValuesToFavoriteUser(values)
                if(value.userId != -1L) {
                    favoriteProviderDao.insertToFavorite(value)
                }
                else return null
            }
            else -> 0
        }

        context?.contentResolver?.notifyChange(CONTENT_URI, null)
        return Uri.parse("$CONTENT_URI/$added")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val deleted: Int = when (FAVORITE_ID) {
            sUriMatcher.match(uri) -> favoriteProviderDao.removeFromFavorite(
                uri.lastPathSegment.toString().toLong()
            )
            else -> 0
        }

        context?.contentResolver?.notifyChange(CONTENT_URI, null)

        return deleted
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }
}