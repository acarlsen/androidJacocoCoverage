package com.github.acarlsen.android.coverage.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CocktailDto(
    @SerialName("idDrink")
    val id: String,
    @SerialName("strDrink")
    val name: String,
    @SerialName("strInstructions")
    val instructions: String,
    @SerialName("strDrinkThumb")
    val image: String?,
)
