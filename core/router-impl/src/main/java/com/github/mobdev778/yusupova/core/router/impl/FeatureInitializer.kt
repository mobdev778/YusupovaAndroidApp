package com.github.mobdev778.yusupova.core.router.impl

import android.content.Context
import androidx.startup.Initializer
import com.github.mobdev778.yusupova.core.router.api.base.BaseRouter

class FeatureInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        BaseRouter.factory = { parentRounter: BaseRouter? ->
            RouterImpl(parentRounter as? RouterImpl)
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}
