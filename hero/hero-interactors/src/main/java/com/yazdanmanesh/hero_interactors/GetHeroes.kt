package com.yazdanmanesh.hero_interactors

import com.yazdanmanesh.core.DataState
import com.yazdanmanesh.core.ProgressBarState
import com.yazdanmanesh.core.UIComponent
import com.yazdanmanesh.hero_datasource.network.HeroService
import com.yazdanmanesh.hero_domain.Hero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetHeroes(
    private val service: HeroService
) {
    fun execute(): Flow<DataState<List<Hero>>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            val heroes: List<Hero> = try {
                service.getHeroStats()
            } catch (e: Exception) {
                DataState.Response<List<Hero>>(
                    uiComponent = UIComponent.Dialog(
                        title = "Error",
                        description = e.message ?: "Unknown Error"
                    )
                )
                listOf()
            }
            emit(DataState.Data(heroes))
        } catch (e: Exception) {
            emit(
                DataState.Response<List<Hero>>(
                    uiComponent = UIComponent.Dialog(
                        title = "Error",
                        description = e.message ?: "Unknown Error"
                    )
                )
            )
        }finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}