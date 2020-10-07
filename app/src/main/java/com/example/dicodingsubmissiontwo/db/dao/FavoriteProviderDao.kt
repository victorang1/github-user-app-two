package com.example.dicodingsubmissiontwo.db.dao

import android.database.Cursor
import androidx.room.*
import com.example.dicodingsubmissiontwo.db.entity.FavoriteUser

@Dao
interface FavoriteProviderDao {

    @Query("SELECT * FROM favorite")
    fun getAllFavoriteUser(): Cursor

    @Query("SELECT * FROM favorite WHERE userId=:userId LIMIT 1")
    fun getFavoriteUserById(userId: Long): Cursor

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToFavorite(user: FavoriteUser): Long

    @Query("DELETE FROM favorite WHERE userId=:userId")
    fun removeFromFavorite(userId: Long): Int
}