package com.guilhermereisapps.rickandmortykotlin.utils

import com.guilhermereisapps.rickandmortykotlin.data.model.character.Character
import com.guilhermereisapps.rickandmortykotlin.data.model.util.ResultState
import com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.characterdetailsviewmodel.ICharacterDetailsViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeCharacterDetailsViewModel : ICharacterDetailsViewModel {

    private val _characterState = MutableStateFlow(
        ResultState(
            data = Character(
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
            isLoading = false
        )
    )
    override val characterState: StateFlow<ResultState<Character>> = _characterState.asStateFlow()

    override fun fetchCharacterDetails(id: Int) {
        // Simula carregamento de personagem (já inicializado no StateFlow)
    }
}
