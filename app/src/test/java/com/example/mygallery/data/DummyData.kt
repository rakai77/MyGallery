package com.example.mygallery.data

import com.example.mygallery.data.source.remote.dto.PhotosResponse

val listPhoto = listOf(
    PhotosResponse(1, 1, "accusamus beatae ad facilis cum similique qui sunt", "https://via.placeholder.com/600/92c952", "https://via.placeholder.com/150/92c952"),
    PhotosResponse(1, 2, "reprehenderit est deserunt velit ipsam", "https://via.placeholder.com/600/771796", "https://via.placeholder.com/150/771796"),
)

val emptyData = emptyList<PhotosResponse>()