package com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel

import com.guilhermereisapps.rickandmortykotlin.data.model.Filters
import com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.filtersviewmodel.FiltersViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class FiltersViewModelTest {

    private lateinit var viewModel: FiltersViewModel

    @Before
    fun setUp() {
        viewModel = FiltersViewModel()
    }

    @Test
    fun `verifica se os filtros são atualizados corretamente`() = runTest {
        val newFilters = Filters(name = "Rick", status = "Alive")
        viewModel.updateFilters(newFilters)

        val result = viewModel.filters.first()

        assertEquals(newFilters, result)
    }
}
