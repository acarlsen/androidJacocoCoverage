package com.github.acarlsen.android.coverage.data.repositories

import com.github.acarlsen.android.coverage.data.mappers.CocktailMapper
import com.github.acarlsen.android.coverage.data.service.CocktailDBService
import com.github.acarlsen.android.coverage.domain.models.CocktailModel
import com.github.acarlsen.android.coverage.domain.repositories.CocktailDBRepo
import javax.inject.Inject

class CocktailDBRepoImpl @Inject constructor(
    private val apiService: CocktailDBService,
    private val cocktailMapper: CocktailMapper,
) : CocktailDBRepo {
    override suspend fun lookupRandomCocktail(): CocktailModel {
        return cocktailMapper.toCocktailModel(apiService.lookupRandomCocktail().drinks.first())
    }
}
