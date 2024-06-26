package com.github.mobdev778.yusupova.core.router.api.base

import com.github.mobdev778.yusupova.core.router.api.Router
import kotlinx.coroutines.flow.StateFlow

interface BaseRouter : Router {

    val stateFlow: StateFlow<RouterState>

    companion object {

        lateinit var factory: (BaseRouter?) -> BaseRouter
    }
}
