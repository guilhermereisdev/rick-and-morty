package com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.filtersviewmodel

import androidx.lifecycle.ViewModel
import com.guilhermereisapps.rickandmortykotlin.data.model.Filters
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FiltersViewModel : ViewModel(), IFiltersViewModel {
    private val _filters = MutableStateFlow(Filters())
    override val filters: StateFlow<Filters> = _filters

    override fun updateFilters(newFilters: Filters) {
        _filters.value = newFilters
    }
}
