package com.guilhermereisapps.rickandmortykotlin.data.model.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Characters(
    val info: Info,
    val results: List<Character>
) : Parcelable
