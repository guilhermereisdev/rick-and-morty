package com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.characterdetailsviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guilhermereisapps.rickandmortykotlin.data.model.character.Character
import com.guilhermereisapps.rickandmortykotlin.data.model.util.ResultState
import com.guilhermereisapps.rickandmortykotlin.domain.usecase.ICharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(
    private val charactersUseCase: ICharactersUseCase
) : ViewModel(), ICharacterDetailsViewModel {
    private val _characterState =
        MutableStateFlow<ResultState<Character>>(ResultState(isLoading = true))
    override var characterState: StateFlow<ResultState<Character>> = _characterState.asStateFlow()

    override fun fetchCharacterDetails(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            charactersUseCase.getCharacterById(id).collect { result ->
                _characterState.value = result
            }
        }
    }
}
