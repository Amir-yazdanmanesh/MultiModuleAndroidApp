apply {
    from("$rootDir/android-library-build.gradle")
}

plugins {
   id(SqlDelight.plugin)
}

dependencies {
    "implementation"(project(Modules.heroDomain))

    "implementation"(Ktor.core)
    "implementation"(Ktor.clientSerialization)
    "implementation"(Ktor.android)

     "implementation"(SqlDelight.runtime)

}

sqldelight{
    database("HeroDatabase"){
        packageName = "com.yazdanmanesh.hero_datasource.cache"
        sourceFolders = listOf("sqldelight")
    }
}

