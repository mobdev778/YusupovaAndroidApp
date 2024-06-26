package com.github.mobdev778.yusupova.core.router.api

import androidx.compose.runtime.Composable

interface ScreenId {

    val key
        get() = this::class.java.simpleName

    interface Args
    interface Result

    object NoArgs : Args
    object NoResult : Result

    object Comparator : kotlin.Comparator<ScreenId> {

        override fun compare(a: ScreenId, b: ScreenId): Int {
            if (a == AnyScreenId || b == AnyScreenId) return 0
            return a.key.compareTo(b.key)
        }
    }

    companion object {

        private val map = HashMap<String, @Composable (args: Args) -> Unit>()

        @JvmStatic
        fun <T : ScreenId> register(id: T, composable: @Composable (args: Args) -> Unit) {
            map[id.key] = composable
        }

        @JvmStatic
        fun <T : ScreenId> composable(id: T): (@Composable (args: Args) -> Unit)? {
            return map[id.key]
        }
    }
}
