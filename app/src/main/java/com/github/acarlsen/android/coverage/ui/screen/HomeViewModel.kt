package com.github.acarlsen.android.coverage.ui.screen

import androidx.lifecycle.ViewModel
import com.github.acarlsen.android.coverage.util.GreetingGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

const val NumberOfGreetings = 3

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private var greetingIndex = 0
    private val _uiState = MutableStateFlow(HomeUiState(greeting = GreetingGenerator.getGreeting(greetingIndex)))
    val uiState = _uiState.asStateFlow()

    fun toggleGreeting() {
        greetingIndex = if (greetingIndex == NumberOfGreetings) 0 else greetingIndex + 1
        _uiState.update {
            it.copy(greeting = GreetingGenerator.getGreeting(greetingIndex))
        }
    }
}

data class HomeUiState(
    val greeting: String,
)
