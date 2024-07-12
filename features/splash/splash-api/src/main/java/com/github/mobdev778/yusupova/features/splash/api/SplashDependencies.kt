package com.github.mobdev778.yusupova.features.splash.api

import com.github.mobdev778.yusupova.core.network.di.NetworkComponent
import com.github.mobdev778.yusupova.features.dependencies.FeatureDependency

interface SplashDependencies : FeatureDependency {

    val networkComponent: NetworkComponent
}
