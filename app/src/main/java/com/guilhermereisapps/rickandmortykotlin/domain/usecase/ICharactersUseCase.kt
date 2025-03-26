package com.guilhermereisapps.rickandmortykotlin.domain.usecase

import androidx.paging.PagingData
import com.guilhermereisapps.rickandmortykotlin.data.model.Filters
import com.guilhermereisapps.rickandmortykotlin.data.model.character.Character
import com.guilhermereisapps.rickandmortykotlin.data.model.util.ResultState
import kotlinx.coroutines.flow.Flow

interface ICharactersUseCase {
//    suspend fun getAllCharacters(filters: Filters?): Flow<ResultState<Characters>>
    suspend fun getAllCharacters(filters: Filters?): Flow<PagingData<Character>>

    suspend fun getCharacterById(id: Int): Flow<ResultState<Character>>
}
