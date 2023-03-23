package com.github.acarlsen.android.coverage.domain.repositories

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class GreetingGeneratorTest {

    @Test
    fun handleNormalCase_isCorrect() {
        val result = GreetingGenerator.getGreeting(3)
        assertThat(result).isEqualTo("Hello reader")
    }

    @Test
    fun handleNegativeOutOfBounds_isCorrect() {
        val result = GreetingGenerator.getGreeting(-4)
        assertThat(result).isEqualTo("Hello world")
    }

    @Test
    fun handlePositiveOutOfBounds_isCorrect() {
        val result = GreetingGenerator.getGreeting(5)
        assertThat(result).isEqualTo("Hello world")
    }
}
