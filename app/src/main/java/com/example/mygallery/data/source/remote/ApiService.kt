package com.example.mygallery.data.source.remote

import com.example.mygallery.data.source.remote.dto.PhotosResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/photos")
    suspend fun getPhotos() : List<PhotosResponse>
}