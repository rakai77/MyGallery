package com.example.mygallery.domain.model

import com.example.mygallery.data.source.local.room.UserEntity

data class User(
    val email: String,
    val password: String,
    val name: String,
    val phoneNumber: String,
)

fun User.toEntity() = UserEntity (
    this.email,
    this.password,
    this.name,
    this.phoneNumber
)