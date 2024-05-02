package com.example.mygallery.data.source.remote

import com.example.mygallery.data.source.remote.dto.PhotosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/photos")
    suspend fun getPhotos(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ) : Response<List<PhotosResponse>>
}