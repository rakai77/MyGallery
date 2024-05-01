package com.example.mygallery.domain.repository

import com.example.mygallery.data.source.Resource
import com.example.mygallery.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun login(email: String, password: String) : Flow<Resource<User>>

    suspend fun register(user: User) : Flow<Resource<User>>
}