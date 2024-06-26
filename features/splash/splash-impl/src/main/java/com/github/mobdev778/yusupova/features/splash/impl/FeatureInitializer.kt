package com.github.mobdev778.yusupova.features.splash.impl

import android.content.Context
import androidx.startup.Initializer
import com.github.mobdev778.yusupova.core.router.api.ScreenId
import com.github.mobdev778.yusupova.features.splash.api.SplashScreenId

class FeatureInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        ScreenId.register(SplashScreenId) {
            SplashScreen()
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}
