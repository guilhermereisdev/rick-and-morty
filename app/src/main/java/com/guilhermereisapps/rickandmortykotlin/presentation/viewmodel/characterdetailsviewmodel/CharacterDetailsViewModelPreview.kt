package com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.characterdetailsviewmodel

import androidx.lifecycle.ViewModel
import com.guilhermereisapps.rickandmortykotlin.data.model.character.Character
import com.guilhermereisapps.rickandmortykotlin.data.model.util.ResultState
import com.guilhermereisapps.rickandmortykotlin.data.model.util.singleCharacterMock
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CharacterDetailsViewModelPreview() : ViewModel(), ICharacterDetailsViewModel {
    override val characterState: StateFlow<ResultState<Character>> =
        MutableStateFlow(ResultState(data = singleCharacterMock, isLoading = false))

    override fun fetchCharacterDetails(id: Int) {}
}
