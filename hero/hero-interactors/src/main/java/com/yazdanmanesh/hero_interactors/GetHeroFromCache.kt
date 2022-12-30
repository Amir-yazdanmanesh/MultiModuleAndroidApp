package com.yazdanmanesh.hero_interactors

import com.yazdanmanesh.core.DataState
import com.yazdanmanesh.core.ProgressBarState
import com.yazdanmanesh.core.UIComponent
import com.yazdanmanesh.hero_datasource.cache.HeroCache
import com.yazdanmanesh.hero_domain.Hero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetHeroFromCache(
    private val cache: HeroCache,
) {
    fun execute(
        id: Int,
    ): Flow<DataState<Hero>> = flow {
        try {
            val cacheHero =
                cache.getHero(id) ?: throw Exception("That hero does not exist in the cache.")
            emit(DataState.Data(cacheHero))
        } catch (e: Exception) {
            emit(
                DataState.Response<Hero>(
                    uiComponent = UIComponent.Dialog(
                        title = "Error",
                        description = e.message ?: "Unknown Error"
                    )
                )
            )
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }

}