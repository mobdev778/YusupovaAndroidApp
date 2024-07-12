package com.github.mobdev778.yusupova.features.dependencies

import kotlin.reflect.KClass

object DependenciesRegistry {

    private val map = HashMap<KClass<*>, FeatureDependency>()

    fun <T : FeatureDependency> addDependency(clazz: KClass<T>, dependency: FeatureDependency) {
        map.put(clazz, dependency)
    }

    fun <T : FeatureDependency> getDependency(clazz: KClass<T>): T {
        return (map[clazz] as? T) ?: throw IllegalStateException("Unable to find dependency for $clazz")
    }
}
