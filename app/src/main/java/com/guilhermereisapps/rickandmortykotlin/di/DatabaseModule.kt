package com.guilhermereisapps.rickandmortykotlin.di

import android.app.Application
import androidx.room.Room
import com.guilhermereisapps.rickandmortykotlin.data.local.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get<Application>(),
            AppDatabase::class.java, "rick_and_morty_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().characterDao() }
}
