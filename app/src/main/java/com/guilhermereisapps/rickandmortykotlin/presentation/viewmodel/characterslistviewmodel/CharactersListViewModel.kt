package com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.characterslistviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.guilhermereisapps.rickandmortykotlin.data.model.Filters
import com.guilhermereisapps.rickandmortykotlin.domain.usecase.ICharactersUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest

class CharactersListViewModel(
    private val charactersUseCase: ICharactersUseCase
) : ViewModel(), ICharactersListViewModel {

    private val _filters = MutableStateFlow(Filters())
    override val filters: StateFlow<Filters> = _filters

    @OptIn(ExperimentalCoroutinesApi::class)
    override val charactersPaging = _filters.flatMapLatest { filters ->
        charactersUseCase.getAllCharacters(filters)
    }.cachedIn(viewModelScope)

    override fun refreshCharacters(filters: Filters?) {
        _filters.value = filters ?: Filters()
    }
}
