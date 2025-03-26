package com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.characterdetailsviewmodel

import com.guilhermereisapps.rickandmortykotlin.data.model.character.Character
import com.guilhermereisapps.rickandmortykotlin.data.model.util.ResultState
import kotlinx.coroutines.flow.StateFlow

interface ICharacterDetailsViewModel {
    val characterState: StateFlow<ResultState<Character>>

    fun fetchCharacterDetails(id: Int)
}
