package com.potados.retrofit

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

class ThisApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ThisApp)
            modules(modules)
        }
    }
}