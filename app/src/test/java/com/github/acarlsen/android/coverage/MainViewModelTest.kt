package com.github.acarlsen.android.coverage

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MainViewModelTest {

    @Test
    fun handleInitialCase_isCorrect() {
        val sut = MainViewModel()
        assertThat(sut.uiState.value.greeting).isEqualTo("Hello world")
    }

    @Test
    fun handleFirstToggleCase_isCorrect() {
        val sut = MainViewModel()
        sut.toggleGreeting()
        assertThat(sut.uiState.value.greeting).isEqualTo("Hello stranger")
    }

    @Test
    fun handleManyTogglesCase_isCorrect() {
        val sut = MainViewModel()
        sut.toggleGreeting()
        sut.toggleGreeting()
        sut.toggleGreeting()
        sut.toggleGreeting()
        assertThat(sut.uiState.value.greeting).isEqualTo("Hello world")
    }
}
