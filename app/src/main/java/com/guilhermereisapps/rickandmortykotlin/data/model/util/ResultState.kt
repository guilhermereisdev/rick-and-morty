package com.guilhermereisapps.rickandmortykotlin.data.model.util

data class ResultState<T>(
    val data: T? = null,
    val error: String? = null,
    val isLoading: Boolean = false
)
