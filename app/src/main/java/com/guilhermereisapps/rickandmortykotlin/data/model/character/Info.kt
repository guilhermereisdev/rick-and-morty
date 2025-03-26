package com.guilhermereisapps.rickandmortykotlin.data.model.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: String? = null
) : Parcelable
