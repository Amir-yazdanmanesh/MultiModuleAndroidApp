package com.yazdanmanesh.ui_heroDetail.ui

import com.yazdanmanesh.core.ProgressBarState
import com.yazdanmanesh.hero_domain.Hero

data class HeroDetailState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val hero: Hero? = null,
)