package com.example.mygallery.presentation.register

import com.example.mygallery.domain.model.User
import com.example.mygallery.presentation.login.LoginUiState

sealed class RegisterUiState {
    class Success(val data: User): RegisterUiState()

    class Error(val message: String): RegisterUiState()

    object Idle: RegisterUiState()

    object Loading: RegisterUiState()
}