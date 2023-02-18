package com.github.acarlsen.android.coverage.ui.screen.cocktail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.acarlsen.android.coverage.domain.models.CocktailModel
import com.github.acarlsen.android.coverage.domain.usecases.GetRandomCocktailUseCase
import com.github.acarlsen.android.coverage.ui.hilt.IoDispatcher
import com.github.acarlsen.android.coverage.ui.util.ViewModelUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailViewModel @Inject constructor(
    private val useCase: GetRandomCocktailUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _uiState = MutableStateFlow<ViewModelUiState<CocktailModel>>(ViewModelUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getNewCocktail()
    }

    fun getNewCocktail() {
        viewModelScope.launch(dispatcher) {
            try {
                _uiState.update { ViewModelUiState.Loading }
                _uiState.update { ViewModelUiState.Success(useCase.execute()) }
            } catch (@Suppress("TooGenericExceptionCaught") e: Exception) {
                _uiState.update { ViewModelUiState.Error(e.message ?: "", e) }
            }
        }
    }
}
