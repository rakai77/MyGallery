package com.example.mygallery.domain.usecase

import com.example.mygallery.data.source.Resource
import com.example.mygallery.domain.model.Photos
import kotlinx.coroutines.flow.Flow

interface PhotoUseCase {
    suspend fun getPhotos() : Flow<Resource<List<Photos>>>
}