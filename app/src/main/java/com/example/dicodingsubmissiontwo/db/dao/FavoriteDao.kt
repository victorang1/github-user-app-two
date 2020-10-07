package com.example.dicodingsubmissiontwo.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.dicodingsubmissiontwo.db.entity.FavoriteUser

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite")
    fun getAllFavoriteUser(): LiveData<List<FavoriteUser>>

    @Query("SELECT * FROM favorite WHERE userId=:userId LIMIT 1")
    suspend fun getFavoriteUserById(userId: Long): FavoriteUser

    @Insert(onConflict = REPLACE)
    suspend fun insertToFavorite(user: FavoriteUser)

    @Delete()
    suspend fun removeFromFavorite(user: FavoriteUser)
}