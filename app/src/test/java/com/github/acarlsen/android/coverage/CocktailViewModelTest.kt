package com.github.acarlsen.android.coverage

import com.github.acarlsen.android.coverage.domain.models.CocktailModel
import com.github.acarlsen.android.coverage.domain.repositories.CocktailDBRepo
import com.github.acarlsen.android.coverage.domain.usecases.GetRandomCocktailUseCase
import com.github.acarlsen.android.coverage.ui.screen.cocktail.CocktailViewModel
import com.github.acarlsen.android.coverage.ui.util.ViewModelUiState
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CocktailViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Test
    fun `Verify we call get cocktail on init`() = runTest(testDispatcher) {
        val result = mockk<CocktailModel>()
        val api = mockk<CocktailDBRepo>()
        coEvery { api.lookupRandomCocktail() } returns result

        val sut = CocktailViewModel(
            useCase = GetRandomCocktailUseCase(api),
            testDispatcher
        )

        assertThat(sut.uiState.value).isEqualTo(ViewModelUiState.Loading)
        advanceUntilIdle()
        assertThat(sut.uiState.value).isEqualTo(ViewModelUiState.Success(result))
    }
}
