package com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.characterslistviewmodel

import androidx.paging.PagingData
import com.guilhermereisapps.rickandmortykotlin.data.model.Filters
import com.guilhermereisapps.rickandmortykotlin.data.model.character.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CharactersListViewModelPreview : ICharactersListViewModel {
    override fun refreshCharacters(filters: Filters?) {}
    override val charactersPaging: Flow<PagingData<Character>> =
        MutableStateFlow(PagingData.empty())

    override val filters: StateFlow<Filters> = MutableStateFlow(Filters())
}
