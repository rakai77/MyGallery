package com.example.mygallery.data.interactor

import com.example.mygallery.data.source.Resource
import com.example.mygallery.data.source.remote.dto.PhotosResponse
import com.example.mygallery.domain.model.Photos
import com.example.mygallery.domain.repository.PhotoRepository
import com.example.mygallery.domain.usecase.PhotoUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotoInteractor @Inject constructor(
    private val photoRepository: PhotoRepository
) : PhotoUseCase{
    override suspend fun getPhotos(): Flow<Resource<List<PhotosResponse>>> {
        return photoRepository.getPhoto()
    }
}