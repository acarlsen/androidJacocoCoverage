package com.github.acarlsen.android.coverage.ui.hilt

import com.github.acarlsen.android.coverage.data.repositories.CocktailDBRepoImpl
import com.github.acarlsen.android.coverage.domain.repositories.CocktailDBRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ServiceModule {
    @Binds
    fun bindACocktailDBRepo(
        cocktailDBRepoImpl: CocktailDBRepoImpl
    ): CocktailDBRepo
}
