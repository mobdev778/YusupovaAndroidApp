import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.android.tools.common)
}

kotlin {
    sourceSets.all {
        kotlin.srcDir("build/generated/ksp/$name/kotlin")
    }
    compilerOptions {
        freeCompilerArgs.addAll("-Xcontext-receivers")
    }
}

gradlePlugin {
    val path = "com.github.mobdev778.yusupova.convention.plugins"
    plugins {
        register("AndroidLibraryConventionPlugin.kt") {
            id = "android.library.convention.plugin"
            implementationClass = "$path.android.AndroidLibraryConventionPlugin"
            version = 1.0
        }
        register("AndroidApplicationConventionPlugin.kt") {
            id = "android.application.convention.plugin"
            implementationClass = "$path.android.AndroidApplicationConventionPlugin"
            version = 1.0
        }
    }
}
