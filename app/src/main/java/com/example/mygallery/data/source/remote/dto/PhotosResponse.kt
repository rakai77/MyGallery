package com.example.mygallery.data.source.remote.dto

import com.example.mygallery.domain.model.Photos
import com.google.gson.annotations.SerializedName

data class PhotosResponse(

	@field:SerializedName("albumId")
	val albumId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("thumbnailUrl")
	val thumbnailUrl: String? = null
)

fun PhotosResponse.toDomain() = Photos(
	this.albumId ?: 0,
	this.id ?: 0,
	this.title ?: "",
	this.url ?: "",
	this.thumbnailUrl ?: ""
)
