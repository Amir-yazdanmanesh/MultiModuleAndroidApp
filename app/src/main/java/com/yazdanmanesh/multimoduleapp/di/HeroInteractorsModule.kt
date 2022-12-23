package com.yazdanmanesh.multimoduleapp.di

import android.app.Application
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.yazdanmanesh.hero_interactors.HeroInteractions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HeroInteractorsModule {

    @Provides
    @Singleton
    @Named("heroAndroidSqlDriver")
    fun provideAndroidDriver(app: Application): SqlDriver {
        return AndroidSqliteDriver(
            schema = HeroInteractions.schema,
            context = app,
            name = HeroInteractions.dbName
        )
    }

    @Provides
    @Singleton
    fun providerHeroIntractors(
        @Named("heroAndroidSqlDriver") sqlDriver: SqlDriver
    ): HeroInteractions {
        return HeroInteractions.build(sqlDriver = sqlDriver)
    }
}