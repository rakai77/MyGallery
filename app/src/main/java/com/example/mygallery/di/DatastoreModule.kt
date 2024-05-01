package com.example.mygallery.di

import android.content.Context
import com.example.mygallery.data.source.local.datastore.GalleryDatastore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatastoreModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): GalleryDatastore {
        return GalleryDatastore(context)
    }
}