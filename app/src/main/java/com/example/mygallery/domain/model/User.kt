package com.example.mygallery.domain.model

data class User(
    val email: String,
    val password: String,
    val name: String,
    val phoneNumber: String,
    val role: String
)