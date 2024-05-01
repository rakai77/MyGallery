package com.example.mygallery.data.interactor

import com.example.mygallery.data.source.Resource
import com.example.mygallery.domain.repository.UserRepository
import com.example.mygallery.domain.usecase.UserUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserInteractor @Inject constructor(
    private val userRepository: UserRepository
) : UserUseCase {
    override suspend fun isUserLogged(): Flow<Resource<Boolean>> {
        return userRepository.isUserLoggedIn()
    }

    override suspend fun storeEmail(email: String) {
        return userRepository.storeEmail(email)
    }

    override suspend fun logout() {
        return userRepository.logout()
    }
}