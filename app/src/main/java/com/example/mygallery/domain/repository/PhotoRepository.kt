package com.example.mygallery.domain.repository

import com.example.mygallery.data.source.Resource
import com.example.mygallery.data.source.remote.dto.PhotosResponse
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {

    suspend fun getPhoto() : Flow<Resource<List<PhotosResponse>>>
}