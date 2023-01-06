package com.github.acarlsen.android.coverage

object GreetingGenerator {
    private val greetings = listOf(
        "Hello world",
        "Hello stranger",
        "Hello developer",
        "Hello reader",
    )

    fun getGreeting(index: Int): String {
        if (greetings.indices.contains(index)) {
            return greetings[index]
        }
        return greetings[0]
    }
}
