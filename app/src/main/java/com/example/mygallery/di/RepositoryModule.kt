package com.example.mygallery.di

import com.example.mygallery.data.repository.AuthRepositoryImpl
import com.example.mygallery.data.repository.UserRepositoryImpl
import com.example.mygallery.domain.repository.AuthRepository
import com.example.mygallery.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideAuthRepository(authRepository: AuthRepositoryImpl) : AuthRepository

    @Binds
    abstract fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl) : UserRepository
}