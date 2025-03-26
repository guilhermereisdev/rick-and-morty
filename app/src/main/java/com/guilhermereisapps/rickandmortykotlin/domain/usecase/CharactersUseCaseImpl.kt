package com.guilhermereisapps.rickandmortykotlin.domain.usecase

import androidx.paging.PagingData
import com.guilhermereisapps.rickandmortykotlin.data.model.Filters
import com.guilhermereisapps.rickandmortykotlin.data.model.character.Character
import com.guilhermereisapps.rickandmortykotlin.domain.repository.ICharactersRepository
import kotlinx.coroutines.flow.Flow

class CharactersUseCaseImpl(
    private val repository: ICharactersRepository
) : ICharactersUseCase {
    override suspend fun getAllCharacters(filters: Filters?): Flow<PagingData<Character>> {
        return repository.getAllCharacters(filters)
    }

    override suspend fun getCharacterById(id: Int) = repository.getCharacterById(id)
}
