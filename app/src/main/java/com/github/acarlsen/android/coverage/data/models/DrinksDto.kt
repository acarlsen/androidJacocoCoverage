package com.github.acarlsen.android.coverage.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DrinksDto(
    @SerialName("drinks")
    val drinks: List<CocktailDto>,
)
