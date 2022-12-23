package com.yazdanmanesh.ui_heroList.ui

import com.yazdanmanesh.core.ProgressBarState
import com.yazdanmanesh.hero_domain.Hero

data class HeroListState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val heroes: List<Hero> = listOf()
)