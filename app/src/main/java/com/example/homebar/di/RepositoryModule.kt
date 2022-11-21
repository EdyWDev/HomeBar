package com.example.homebar.di

import com.example.homebar.recipesearch.service.HomeBarRepository
import com.example.homebar.recipesearch.service.RecipeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideHomeBarRepository(
        homeBarService: RecipeService
    ): HomeBarRepository =
        HomeBarRepository(homeBarService)
}