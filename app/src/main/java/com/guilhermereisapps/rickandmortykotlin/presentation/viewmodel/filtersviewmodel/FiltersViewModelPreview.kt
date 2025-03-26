package com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.filtersviewmodel

import com.guilhermereisapps.rickandmortykotlin.data.model.Filters
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FiltersViewModelPreview : IFiltersViewModel {
    override val filters: StateFlow<Filters> = MutableStateFlow(Filters())

    override fun updateFilters(newFilters: Filters) {}
}
