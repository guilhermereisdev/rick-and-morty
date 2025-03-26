package com.guilhermereisapps.rickandmortykotlin.presentation.util

fun String.extractEpisodeNumber(): String = this.substringAfterLast("/")
