package com.github.acarlsen.android.coverage.domain.usecases

import com.github.acarlsen.android.coverage.domain.models.CocktailModel
import com.github.acarlsen.android.coverage.domain.repositories.CocktailDBRepo
import javax.inject.Inject

class GetRandomCocktailUseCase @Inject constructor(
    private val api: CocktailDBRepo
) {
    suspend fun execute(): CocktailModel {
        return api.lookupRandomCocktail()
    }
}
