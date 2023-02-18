package com.github.acarlsen.android.coverage.ui.hilt

import com.github.acarlsen.android.coverage.data.service.CocktailDBService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun bindCocktailDBService(): CocktailDBService {
        return CocktailDBService.create()
    }
}
