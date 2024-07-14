plugins {
    alias(libs.plugins.android.library.convention.plugin)
    id(libs.plugins.kotlin.kapt.get().pluginId)
}

android {
    namespace = "com.github.mobdev778.yusupova.features.splash.impl"

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

dependencies {
    implementation(project(":core:design-system"))
    implementation(project(":core:domain"))
    implementation(project(":core:network"))
    implementation(project(":core:router-api"))
    implementation(project(":features:common"))
    implementation(project(":features:splash:splash-api"))

    implementation(libs.moshi.kotlin)
    implementation(libs.retrofit.retrofit)

    implementation(libs.dagger.dagger)
    kapt(libs.dagger.compiler)

    implementation(libs.androidx.lifecycle.viewmodel.android)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.ui.tooling.preview.android)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.startup)
}
