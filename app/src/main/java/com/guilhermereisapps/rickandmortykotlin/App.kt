package com.guilhermereisapps.rickandmortykotlin

import android.app.Application
import com.guilhermereisapps.rickandmortykotlin.di.appModule
import com.guilhermereisapps.rickandmortykotlin.di.databaseModule
import com.guilhermereisapps.rickandmortykotlin.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, networkModule, databaseModule))
        }
    }
}
