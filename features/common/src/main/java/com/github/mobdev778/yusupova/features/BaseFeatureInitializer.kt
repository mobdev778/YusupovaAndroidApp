package com.github.mobdev778.yusupova.features

import androidx.startup.Initializer
import com.github.mobdev778.yusupova.features.dependencies.FeatureDependency
import kotlin.reflect.KClass

abstract class BaseFeatureInitializer<T>(
    private val clazz: KClass<out FeatureDependency>? = null
) : Initializer<T> {

    init {
        val clazz = this::class.java
        clazz.interfaces.forEach {
            map[it.kotlin] = clazz
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        if (clazz != null) {
            val depClass = findInitializer(clazz::class)
            if (depClass != null) {
                return mutableListOf()
            }
        }
        return mutableListOf()
    }

    companion object {
        private val map = HashMap<KClass<*>, Class<out Initializer<*>>>()

        fun findInitializer(interfaceClass: KClass<*>): Class<out Initializer<*>>? {
            return map[interfaceClass]
        }
    }
}
