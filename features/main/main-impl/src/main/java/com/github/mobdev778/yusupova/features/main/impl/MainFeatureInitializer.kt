package com.github.mobdev778.yusupova.features.main.impl

import android.content.Context
import com.github.mobdev778.yusupova.core.router.api.ScreenId
import com.github.mobdev778.yusupova.features.BaseFeatureInitializer
import com.github.mobdev778.yusupova.features.main.api.MainDependencies
import com.github.mobdev778.yusupova.features.main.api.MainScreenId
import com.github.mobdev778.yusupova.features.main.impl.presentation.MainScreen

class MainFeatureInitializer : BaseFeatureInitializer<Unit>(MainDependencies::class.java) {

    override fun create(context: Context) {
        ScreenId.register(MainScreenId) {
            MainScreen()
        }
    }
}
