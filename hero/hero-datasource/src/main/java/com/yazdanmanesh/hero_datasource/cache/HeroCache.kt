package com.yazdanmanesh.hero_datasource.cache

import com.squareup.sqldelight.db.SqlDriver
import com.yazdanmanesh.hero_domain.Hero

interface HeroCache {

    suspend fun getHero(id: Int): Hero?

    suspend fun removeHero(id: Int)

    suspend fun selectAll(): List<Hero>

    suspend fun insert(hero: Hero)

    suspend fun insert(heroes: List<Hero>)

    suspend fun searchByName(localizedName: String): List<Hero>

    suspend fun searchByAttr(primaryAttr: String): List<Hero>

    suspend fun searchByAttackType(attackType: String): List<Hero>

    // Can select multiple roles
    suspend fun searchByRole(
        carry: Boolean = false,
        escape: Boolean = false,
        nuker: Boolean = false,
        initiator: Boolean = false,
        durable: Boolean = false,
        disabler: Boolean = false,
        jungler: Boolean = false,
        support: Boolean = false,
        pusher: Boolean = false,
    ): List<Hero>

    companion object Factory {
        fun build(sqlDriver: SqlDriver): HeroCache {
            return HeroCacheImpl(HeroDatabase(sqlDriver))
        }
        fun schema(): SqlDriver.Schema {
            return HeroDatabase.Schema
        }

        fun dbName(): String {
            return "heros.db"
        }
    }

}
