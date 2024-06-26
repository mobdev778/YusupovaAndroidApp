pluginManagement {
    includeBuild("build-logic")

    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "YusupovaApp"

private val maxDepth = 3

fun enumSubModules(modulesDir: String): Array<String> {
    val featuresDir = File(rootDir.absolutePath, modulesDir)
    val featuresDirString = featuresDir.absolutePath
    return ArrayList<File>()
        .apply { findModules(featuresDir) }
        .map {
            val path = it.absolutePath.substring(featuresDirString.length).replace(File.separator, ":")
            ":$modulesDir$path"
        }
        .toTypedArray()
}

fun ArrayList<File>.findModules(file: File, depth: Int = 0) {
    when {
        depth >= maxDepth || file.isFile -> return
        file.isGradleModuleDir() -> add(file)
        else -> file.listFiles()?.forEach {
            findModules(it, depth + 1)
        }
    }
}

fun File.isGradleModuleDir(): Boolean {
    return File(this, "src").isDirectory && File(this, "build.gradle.kts").isFile
}

include(":app")
include(*enumSubModules("core"))
include(*enumSubModules("features"))
