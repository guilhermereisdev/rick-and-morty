package com.guilhermereisapps.rickandmortykotlin.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.guilhermereisapps.rickandmortykotlin.data.local.converter.CharacterConverters
import com.guilhermereisapps.rickandmortykotlin.data.model.character.Location
import com.guilhermereisapps.rickandmortykotlin.data.model.character.Origin

@Entity(tableName = "characters")
@TypeConverters(CharacterConverters::class)
data class CharacterEntity(
    @PrimaryKey val id: Int? = null,
    val name: String? = null,
    val status: String? = null,
    val species: String? = null,
    val location: Location? = null,
    val origin: Origin? = null,
    val gender: String? = null,
    val image: String? = null,
    val episode: List<String>? = emptyList()
)
