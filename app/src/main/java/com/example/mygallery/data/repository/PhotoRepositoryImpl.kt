package com.example.mygallery.data.repository

import com.example.mygallery.data.source.Resource
import com.example.mygallery.data.source.remote.ApiService
import com.example.mygallery.data.source.remote.SafeApiCall
import com.example.mygallery.domain.model.Photos
import com.example.mygallery.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : PhotoRepository, SafeApiCall {
    override suspend fun getPhoto(): Flow<Resource<List<Photos>>> {
        return flow { safeApiCall { apiService.getPhotos() } }
    }

}