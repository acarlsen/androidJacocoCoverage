package com.github.acarlsen.android.coverage.data.mappers

import com.github.acarlsen.android.coverage.data.models.CocktailDto
import com.github.acarlsen.android.coverage.domain.models.CocktailModel
import javax.inject.Inject

class CocktailMapper @Inject constructor() {
    fun toCocktailModel(dto: CocktailDto): CocktailModel {
        return CocktailModel(
            id = dto.id,
            name = dto.name,
            instructions = dto.instructions,
            image = dto.image
        )
    }
}
