package com.github.mobdev778.yusupova.features.splash.impl

import android.content.Context
import com.github.mobdev778.yusupova.core.router.api.ScreenId
import com.github.mobdev778.yusupova.features.BaseFeatureInitializer
import com.github.mobdev778.yusupova.features.splash.api.SplashDependencies
import com.github.mobdev778.yusupova.features.splash.api.SplashScreenId
import com.github.mobdev778.yusupova.features.splash.impl.presentation.SplashScreen

class SplashFeatureInitializer : BaseFeatureInitializer<Unit>(SplashDependencies::class) {

    override fun create(context: Context) {
        ScreenId.register(SplashScreenId) {
            SplashScreen()
        }
    }
}
