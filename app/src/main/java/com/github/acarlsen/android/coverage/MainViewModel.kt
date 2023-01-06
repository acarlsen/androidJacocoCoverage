package com.github.acarlsen.android.coverage

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    private var greetingIndex = 0
    private val _uiState = MutableStateFlow(MainUiState(greeting = GreetingGenerator.getGreeting(greetingIndex)))
    val uiState: StateFlow<MainUiState> = _uiState

    fun toggleGreeting() {
        greetingIndex = if (greetingIndex == 3) 0 else greetingIndex + 1
        _uiState.update {
            it.copy(greeting = GreetingGenerator.getGreeting(greetingIndex))
        }
    }
}

data class MainUiState(
    val greeting: String,
)
