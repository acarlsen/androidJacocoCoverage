package com.github.acarlsen.android.coverage.ui.util

sealed class ViewModelUiState<out T : Any> {
    data class Success<out T : Any>(val value: T) : ViewModelUiState<T>()
    data class Error(val message: String, val cause: Exception? = null) : ViewModelUiState<Nothing>()
    object Loading : ViewModelUiState<Nothing>()
}
