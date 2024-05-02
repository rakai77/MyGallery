package com.example.mygallery.data.repository

import com.example.mygallery.data.source.Resource
import com.example.mygallery.data.source.remote.ApiService
import com.example.mygallery.data.source.remote.SafeApiCall
import com.example.mygallery.data.source.remote.dto.PhotosResponse
import com.example.mygallery.domain.repository.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : PhotoRepository {
    override suspend fun getPhoto(): Flow<Resource<List<PhotosResponse>>> {
        return flow {
            try {
                val response = apiService.getPhotos()
                if (response.isSuccessful) {
                    val body = response.body()!!
                    emit(Resource.Success(body))
                }
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        emit(Resource.Error(t.code(), t.message() ?: "Ups, something went wrong!"))
                    }
                    is UnknownHostException -> {
                        emit(Resource.Error(null, t.message ?: "Check your internet connection"))
                    }
                    is SocketTimeoutException -> {
                        emit(Resource.Error(null, t.message ?: "Timeout"))
                    }
                    else -> emit(Resource.Error(null, t.message ?: "Something went wrong"))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

}