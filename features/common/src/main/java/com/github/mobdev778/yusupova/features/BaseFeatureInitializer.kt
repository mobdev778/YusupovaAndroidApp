package com.github.mobdev778.yusupova.features

import android.util.Log
import androidx.startup.Initializer
import com.github.mobdev778.yusupova.features.dependencies.FeatureDependency

abstract class BaseFeatureInitializer<T>(
    private val clazz: Class<out FeatureDependency>? = null
) : Initializer<T> {

    init {
        Log.d(TAG, "init(${javaClass.name})")
        register(this::class.java)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        if (clazz != null) {
            Log.d(TAG, "Finding initializer for ${javaClass.name}")
            val depClass = findInitializer(clazz)
            Log.d(TAG, "depClass: $depClass")
            return mutableListOf(depClass)
        }
        return mutableListOf()
    }

    companion object {

        private val TAG = "BaseFeatureInitializer"
        private val map = HashMap<String, Class<out Initializer<*>>>()

        @JvmStatic
        protected fun register(clazz: Class<out Initializer<*>>) {
            Log.d(TAG, "register($clazz)")
            clazz.interfaces.forEach {
                Log.d("BaseFeatureInitializer", "register[$it.name]=$clazz")
                map[it.name] = clazz
            }
        }

        fun findInitializer(interfaceClass: Class<*>): Class<out Initializer<*>> {
            val name = interfaceClass.name
            Log.d(TAG, "findInitializer($name)")
            return map[name] as Class<out Initializer<*>>
        }
    }
}
