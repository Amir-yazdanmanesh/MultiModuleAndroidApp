package com.yazdanmanesh.ui_heroDetail.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun HeroDetail(
    heroDetailState: HeroDetailState,
){
    heroDetailState.hero?.let { hero ->
        Text(text = "${hero.localizedName}")
}?: Text(text = "LOADING...")
}