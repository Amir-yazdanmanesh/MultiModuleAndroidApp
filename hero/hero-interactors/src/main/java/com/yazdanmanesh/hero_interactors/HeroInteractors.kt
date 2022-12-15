package com.yazdanmanesh.hero_interactors

import com.squareup.sqldelight.db.SqlDriver
import com.yazdanmanesh.hero_datasource.cache.HeroCache
import com.yazdanmanesh.hero_datasource.network.HeroService

class HeroInteractions(
    val getHeroes: GetHeroes
) {
    companion object Factory {
        fun build(sqlDriver: SqlDriver): HeroInteractions {
            val service = HeroService.build()
            val cache = HeroCache.build(sqlDriver)
            return HeroInteractions(
                getHeroes = GetHeroes(
                    service = service,
                    cache = cache
                )
            )
        }
        val schema = HeroCache.schema()
        val dbName = HeroCache.dbName()
    }
}