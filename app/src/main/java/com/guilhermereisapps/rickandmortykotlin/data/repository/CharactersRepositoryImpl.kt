package com.guilhermereisapps.rickandmortykotlin.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.guilhermereisapps.rickandmortykotlin.data.local.dao.CharacterDao
import com.guilhermereisapps.rickandmortykotlin.data.model.Filters
import com.guilhermereisapps.rickandmortykotlin.data.model.character.Character
import com.guilhermereisapps.rickandmortykotlin.data.model.util.ResultState
import com.guilhermereisapps.rickandmortykotlin.data.model.util.toCharacter
import com.guilhermereisapps.rickandmortykotlin.data.remote.RemoteService
import com.guilhermereisapps.rickandmortykotlin.domain.repository.ICharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CharactersRepositoryImpl(
    private val apiService: RemoteService,
    private val characterDao: CharacterDao
) : ICharactersRepository {
    override fun getAllCharacters(filters: Filters?): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CharactersPagingSource(
                    apiService,
                    filters ?: Filters(),
                    characterDao
                )
            }
        ).flow
    }

    override suspend fun getCharacterById(id: Int): Flow<ResultState<Character>> = flow {
        emit(ResultState(isLoading = true))

        val localCharacter = characterDao.getCharacterById(id).firstOrNull()
        if (localCharacter != null) {
            emit(ResultState(data = localCharacter.toCharacter(), isLoading = false))
            return@flow
        }

        try {
            val response = apiService.getCharacterById(id)
            if (response.isSuccessful) {
                response.body()?.let { character ->
                    emit(ResultState(data = character, isLoading = false))
                }
            } else {
                emit(
                    ResultState(
                        error = "Erro ${response.code()} - ${response.message()}",
                        isLoading = false
                    )
                )
            }
            return@flow
        } catch (e: IOException) {
            emit(
                ResultState(
                    error = "Erro: Falha de rede. Descrição: ${e.message}",
                    isLoading = false
                )
            )
        } catch (e: HttpException) {
            emit(ResultState(error = "Erro: ${e.message()}", isLoading = false))
        }
    }
}
