package com.yazdanmanesh.ui_heroDetail.ui

sealed class HeroDetailEvents{
    data class GetHeroFromCache(
        val id:Int,
    ):HeroDetailEvents()
}
