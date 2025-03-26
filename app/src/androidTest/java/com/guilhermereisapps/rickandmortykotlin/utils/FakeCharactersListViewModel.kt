package com.guilhermereisapps.rickandmortykotlin.utils

import androidx.paging.PagingData
import com.guilhermereisapps.rickandmortykotlin.data.model.Filters
import com.guilhermereisapps.rickandmortykotlin.data.model.character.Character
import com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.characterslistviewmodel.ICharactersListViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf

class FakeCharactersListViewModel : ICharactersListViewModel {

    private val fakeCharacters = listOf(
        Character(
            id = 1,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            gender = "Male",
            image = "",
            location = null,
            origin = null,
            type = "",
            url = "",
            episode = emptyList()
        ),
        Character(
            id = 2,
            name = "Morty Smith",
            status = "Alive",
            species = "Human",
            gender = "Male",
            image = "",
            location = null,
            origin = null,
            type = "",
            url = "",
            episode = emptyList()
        )
    )

    private val _filters = MutableStateFlow(Filters())
    override val filters: StateFlow<Filters> = _filters

    override val charactersPaging: Flow<PagingData<Character>> = flowOf(PagingData.from(fakeCharacters))

    override fun refreshCharacters(filters: Filters?) {
        _filters.value = filters ?: Filters()
    }
}