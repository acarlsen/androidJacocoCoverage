package com.github.acarlsen.android.coverage.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.acarlsen.android.coverage.domain.models.CocktailModel
import com.github.acarlsen.android.coverage.domain.usecases.GetRandomCocktailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailViewModel @Inject constructor(
    private val useCase: GetRandomCocktailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CocktailUiState>(Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getNewCocktail()
    }

    fun getNewCocktail() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.update { Loading }
                _uiState.update { Success(useCase.execute()) }
            } catch (@Suppress("TooGenericExceptionCaught") e: Exception) {
                _uiState.update { Error(e.message ?: "") }
            }
        }
    }
}

sealed interface CocktailUiState
object Loading : CocktailUiState
data class Error(val message: String) : CocktailUiState
data class Success(val cocktail: CocktailModel) : CocktailUiState
