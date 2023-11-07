package com.aredruss.warmaster

import android.app.Application
import com.aredruss.warmaster.di.dataModule
import com.aredruss.warmaster.di.databaseModule
import com.aredruss.warmaster.di.uiModule
import com.aredruss.warmaster.util.LineLoggingTree
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(LineLoggingTree())
        Timber.i("App started")

        startKoin {
            androidContext(this@App)
            modules(databaseModule, dataModule, uiModule)
        }
    }
}