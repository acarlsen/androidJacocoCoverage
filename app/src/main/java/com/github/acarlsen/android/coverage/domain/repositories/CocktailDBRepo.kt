package com.github.acarlsen.android.coverage.domain.repositories

import com.github.acarlsen.android.coverage.domain.models.CocktailModel

interface CocktailDBRepo {
    suspend fun lookupRandomCocktail(): CocktailModel
}
