package com.github.acarlsen.android.coverage.ui.screen.home

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class HomeViewModelTest {

    @Test
    fun handleInitialCase_isCorrect() {
        val sut = HomeViewModel()
        assertThat(sut.uiState.value.greeting).isEqualTo("Hello world")
    }

    @Test
    fun handleFirstToggleCase_isCorrect() {
        val sut = HomeViewModel()
        sut.toggleGreeting()
        assertThat(sut.uiState.value.greeting).isEqualTo("Hello stranger")
    }

    @Test
    fun handleManyTogglesCase_isCorrect() {
        val sut = HomeViewModel()
        sut.toggleGreeting()
        sut.toggleGreeting()
        sut.toggleGreeting()
        sut.toggleGreeting()
        assertThat(sut.uiState.value.greeting).isEqualTo("Hello world")
    }
}
