plugins {
    alias(libs.plugins.android.application.convention.plugin)
    id(libs.plugins.kotlin.kapt.get().pluginId)
}

android {
    namespace = "com.github.mobdev778.yusupova.app"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.version.get()
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

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

dependencies {

    enumSubModules("core").forEach {
        implementation(project(it))
    }
    enumSubModules("features").forEach {
        implementation(project(it))
    }

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.dagger.dagger)
    implementation(libs.material)

    kapt(libs.dagger.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
