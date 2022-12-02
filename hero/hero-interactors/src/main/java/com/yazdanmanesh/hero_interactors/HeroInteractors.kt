package com.yazdanmanesh.hero_interactors

import com.yazdanmanesh.hero_datasource.network.HeroService

class HeroInteractions(
    val getHeroes: GetHeroes
) {
    companion object Factory{
        fun build():HeroInteractions{
            val service = HeroService.build()
            return HeroInteractions(
                getHeroes = GetHeroes(
                    service = service
                )
            )
        }
    }
}