package com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.characterslistviewmodel

import androidx.paging.PagingData
import com.guilhermereisapps.rickandmortykotlin.data.model.Filters
import com.guilhermereisapps.rickandmortykotlin.data.model.character.Character
import com.guilhermereisapps.rickandmortykotlin.data.model.character.Characters
import com.guilhermereisapps.rickandmortykotlin.data.model.util.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ICharactersListViewModel {
//    val charactersState: StateFlow<ResultState<Characters>>
//
//    fun fetchCharacters(filters: Filters? = null)
    fun refreshCharacters(filters: Filters? = null)

    val charactersPaging: Flow<PagingData<Character>>
    val filters: StateFlow<Filters>
}
