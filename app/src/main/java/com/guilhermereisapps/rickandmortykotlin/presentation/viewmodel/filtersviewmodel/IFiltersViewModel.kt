package com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.filtersviewmodel

import com.guilhermereisapps.rickandmortykotlin.data.model.Filters
import kotlinx.coroutines.flow.StateFlow

interface IFiltersViewModel {
    val filters: StateFlow<Filters>
    fun updateFilters(newFilters: Filters)
}
