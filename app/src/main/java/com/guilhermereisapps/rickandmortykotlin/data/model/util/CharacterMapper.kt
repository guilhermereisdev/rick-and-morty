package com.guilhermereisapps.rickandmortykotlin.data.model.util

import com.guilhermereisapps.rickandmortykotlin.data.local.entity.CharacterEntity
import com.guilhermereisapps.rickandmortykotlin.data.model.character.Character

fun CharacterEntity.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        image = image,
        location = location,
        origin = origin,
        episode = episode,
        type = "",
        url = "",
        created = ""
    )
}

fun Character.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        image = image,
        location = location,
        origin = origin,
        episode = episode,
    )
}
