package com.github.mobdev778.yusupova.app.application

import android.app.Application
import androidx.startup.AppInitializer
import com.github.mobdev778.yusupova.app.di.component.AppComponent
import com.github.mobdev778.yusupova.app.startup.MainAppInitializer

class YusupovaApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = AppInitializer
            .getInstance(this)
            .initializeComponent(MainAppInitializer::class.java)
    }
}
