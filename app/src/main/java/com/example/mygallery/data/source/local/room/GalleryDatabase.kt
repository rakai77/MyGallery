package com.example.mygallery.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class GalleryDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
}