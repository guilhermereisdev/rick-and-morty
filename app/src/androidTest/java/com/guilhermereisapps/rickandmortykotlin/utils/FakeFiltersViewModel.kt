package com.guilhermereisapps.rickandmortykotlin.utils

import com.guilhermereisapps.rickandmortykotlin.data.model.Filters
import com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.filtersviewmodel.IFiltersViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeFiltersViewModel : IFiltersViewModel {

    private val _filters = MutableStateFlow(Filters())
    override val filters: StateFlow<Filters> = _filters.asStateFlow()

    override fun updateFilters(newFilters: Filters) {
        _filters.value = newFilters
    }
}
