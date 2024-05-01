package com.example.mygallery.domain.usecase

import com.example.mygallery.data.source.Resource
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    suspend fun isUserLogged() : Flow<Resource<Boolean>>
    suspend fun storeEmail(email: String)
    suspend fun logout()
}