package com.example.mygallery.presentation.login

import com.example.mygallery.domain.model.User

sealed class LoginUiState {
    class Success(val data: User): LoginUiState()

    class Error(val message: String): LoginUiState()

    object Idle: LoginUiState()

    object Loading: LoginUiState()
}