package com.yazdanmanesh.ui_heroDetail.di

import com.yazdanmanesh.hero_interactors.GetHeroFromCache
import com.yazdanmanesh.hero_interactors.HeroInteractions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HeroDetailModule {

    @Provides
    @Singleton
    fun provideGetHeroFromCache(
        interactions: HeroInteractions
    ): GetHeroFromCache {
        return interactions.getHeroFromCache
    }

}