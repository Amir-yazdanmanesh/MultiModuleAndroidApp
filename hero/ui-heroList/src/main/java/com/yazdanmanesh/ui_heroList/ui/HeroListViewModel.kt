package com.yazdanmanesh.ui_heroList.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.yazdanmanesh.core.DataState
import com.yazdanmanesh.core.Logger
import com.yazdanmanesh.core.UIComponent
import com.yazdanmanesh.hero_interactors.GetHeroes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HeroListViewModel @Inject
constructor(
    private val getHeroes: GetHeroes
) : ViewModel() {
    val state: MutableState<HeroListState> = mutableStateOf(HeroListState())
    val logger = Logger(tag = "HeroListViewModel")

    init {
        getHeroes()
    }

    private fun getHeroes() {
        getHeroes.execute().onEach { dataState ->
            when (dataState) {
                is DataState.Response -> {
                    when (dataState.uiComponent) {
                        is UIComponent.Dialog -> {
                            logger.log((dataState.uiComponent as UIComponent.Dialog).description)
                        }
                        is UIComponent.None -> {
                            logger.log((dataState.uiComponent as UIComponent.None).message)
                        }
                    }
                }
                is DataState.Data -> {
                    Log.e("moz", "${dataState.data} ")
                    state.value = state.value.copy(heroes = dataState.data ?: listOf())
                }

                is DataState.Loading -> {
                    state.value = state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))

    }
}