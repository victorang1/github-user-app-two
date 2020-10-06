package com.example.dicodingsubmissiontwo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dicodingsubmissiontwo.db.dao.FavoriteDao
import com.example.dicodingsubmissiontwo.db.entity.FavoriteUser

@Database(entities = [FavoriteUser::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getFavoriteDao(): FavoriteDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext , AppDatabase::class.java, "GithubDatabase.db")
            .build()
    }
}