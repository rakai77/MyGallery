package com.example.mygallery.di

import android.content.Context
import androidx.room.Room
import com.example.mygallery.data.source.local.room.GalleryDatabase
import com.example.mygallery.data.source.local.room.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : GalleryDatabase = Room.databaseBuilder(
        context,
        GalleryDatabase::class.java,
        "gallery.db"
    ).allowMainThreadQueries().build()

    @Provides
    fun provideUserDao(db: GalleryDatabase): UserDao = db.userDao()
}