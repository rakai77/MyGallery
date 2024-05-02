package com.example.mygallery.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygallery.data.source.Resource
import com.example.mygallery.data.source.remote.dto.toDomain
import com.example.mygallery.domain.usecase.PhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val photoUseCase: PhotoUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Idle)
    val uiState: StateFlow<HomeUiState> get() = _uiState.asStateFlow()

    fun getPhoto() {
        viewModelScope.launch {
            _uiState.emit(HomeUiState.Loading)
            photoUseCase.getPhotos()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            _uiState.value = (HomeUiState.Success(result.data.map { it.toDomain() }))
                        }
                        is Resource.Error -> {
                            _uiState.value = (HomeUiState.Error(result.message))
                        }
                        else -> Unit
                    }
                }
        }
    }
}