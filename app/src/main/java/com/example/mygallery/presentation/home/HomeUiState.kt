package com.example.mygallery.presentation.home

import com.example.mygallery.domain.model.Photos
import com.example.mygallery.domain.model.User
import com.example.mygallery.presentation.login.LoginUiState

sealed class HomeUiState {
    class Success(val data: List<Photos>): HomeUiState()

    class Error(val message: String): HomeUiState()

    object Idle: HomeUiState()

    object Loading: HomeUiState()
}