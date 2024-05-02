package com.example.mygallery.di

import com.example.mygallery.data.interactor.AuthInteractor
import com.example.mygallery.data.interactor.PhotoInteractor
import com.example.mygallery.data.interactor.UserInteractor
import com.example.mygallery.domain.usecase.AuthUseCase
import com.example.mygallery.domain.usecase.PhotoUseCase
import com.example.mygallery.domain.usecase.UserUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun provideAuthUseCase(authInteractor: AuthInteractor): AuthUseCase

    @Binds
    abstract fun provideUserUseCase(userInteractor: UserInteractor) : UserUseCase

    @Binds
    abstract fun providePhotoUseCase(photoInteractor: PhotoInteractor) : PhotoUseCase
}