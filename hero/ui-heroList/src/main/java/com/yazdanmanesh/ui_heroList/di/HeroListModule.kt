package com.yazdanmanesh.ui_heroList.di

import com.yazdanmanesh.core.Logger
import com.yazdanmanesh.hero_interactors.GetHeroes
import com.yazdanmanesh.hero_interactors.HeroInteractions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HeroListModule {

    @Provides
    @Singleton
    @Named("heroListLogger")
    fun provideLogger(): Logger {
        return Logger(
            tag = "HeroList",
            isDebug = true,
        )
    }

    @Provides
    @Singleton
    fun provideGetHeroes(
        interactions: HeroInteractions
    ): GetHeroes {
        return interactions.getHeroes
    }
}