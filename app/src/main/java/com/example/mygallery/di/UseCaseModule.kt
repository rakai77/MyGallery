package com.example.mygallery.di

import com.example.mygallery.data.interactor.AuthInteractor
import com.example.mygallery.domain.usecase.AuthUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun provideAuthUseCase(authInteractor: AuthInteractor): AuthUseCase
}