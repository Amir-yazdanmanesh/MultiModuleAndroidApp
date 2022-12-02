apply {
    from("$rootDir/android-library-build.gradle")
}

dependencies{
"implementation"(project(Modules.core))
"implementation"(project(Modules.heroDataSource))
"implementation"(project(Modules.heroDomain))

"implementation"(Kotlinx.coroutinesCore)
}
