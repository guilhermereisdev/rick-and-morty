package com.guilhermereisapps.rickandmortykotlin.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.guilhermereisapps.rickandmortykotlin.data.model.character.Location
import com.guilhermereisapps.rickandmortykotlin.data.model.character.Origin

class CharacterConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromLocation(location: Location): String {
        return gson.toJson(location)
    }

    @TypeConverter
    fun toLocation(locationString: String): Location {
        val type = object : TypeToken<Location>() {}.type
        return gson.fromJson(locationString, type)
    }

    @TypeConverter
    fun fromOrigin(origin: Origin): String {
        return gson.toJson(origin)
    }

    @TypeConverter
    fun toOrigin(originString: String): Origin {
        val type = object : TypeToken<Origin>() {}.type
        return gson.fromJson(originString, type)
    }

    @TypeConverter
    fun fromEpisode(episode: List<String>): String {
        return gson.toJson(episode)
    }

    @TypeConverter
    fun toEpisode(episodeString: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(episodeString, type)
    }
}
