package com.guilhermereisapps.rickandmortykotlin.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.guilhermereisapps.rickandmortykotlin.data.local.converter.CharacterConverters
import com.guilhermereisapps.rickandmortykotlin.data.local.dao.CharacterDao
import com.guilhermereisapps.rickandmortykotlin.data.local.entity.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(CharacterConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}
