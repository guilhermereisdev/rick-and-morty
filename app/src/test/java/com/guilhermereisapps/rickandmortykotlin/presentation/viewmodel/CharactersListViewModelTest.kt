package com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel

import androidx.paging.PagingData
import com.guilhermereisapps.rickandmortykotlin.data.model.Filters
import com.guilhermereisapps.rickandmortykotlin.domain.usecase.ICharactersUseCase
import com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.characterslistviewmodel.CharactersListViewModel
import com.guilhermereisapps.rickandmortykotlin.utils.TestCoroutineRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharactersListViewModelTest {

    @get:Rule
    val dispatcherRule = TestCoroutineRule() // 🔹 Controla as coroutines nos testes

    private lateinit var viewModel: CharactersListViewModel
    private val charactersUseCase: ICharactersUseCase = mockk()

    @Before
    fun setUp() {
        coEvery { charactersUseCase.getAllCharacters(any()) } returns flowOf(PagingData.from(emptyList()))
        viewModel = CharactersListViewModel(charactersUseCase)
    }

    @Test
    fun `verifica se os filtros são atualizados corretamente`() = runTest {
        val newFilters = Filters(name = "Rick")
        viewModel.refreshCharacters(newFilters)

        assertEquals(newFilters, viewModel.filters.value)
    }
}
