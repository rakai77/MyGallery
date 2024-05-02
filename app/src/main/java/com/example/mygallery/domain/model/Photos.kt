package com.example.mygallery.domain.model

data class Photos(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)