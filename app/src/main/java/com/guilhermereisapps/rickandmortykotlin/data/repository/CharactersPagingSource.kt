package com.guilhermereisapps.rickandmortykotlin.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.guilhermereisapps.rickandmortykotlin.data.local.dao.CharacterDao
import com.guilhermereisapps.rickandmortykotlin.data.local.entity.CharacterEntity
import com.guilhermereisapps.rickandmortykotlin.data.model.Filters
import com.guilhermereisapps.rickandmortykotlin.data.model.character.Character
import com.guilhermereisapps.rickandmortykotlin.data.remote.RemoteService
import kotlinx.coroutines.flow.firstOrNull

class CharactersPagingSource(
    private val remoteService: RemoteService,
    private val filters: Filters,
    private val characterDao: CharacterDao,
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val currentPage = params.key ?: 1
            val response = remoteService.getAllCharacters(
                name = filters.name,
                status = filters.status,
                species = filters.species,
                gender = filters.gender,
                page = currentPage
            )

            if (response.isSuccessful) {
                val data = response.body()
                val characters = data?.results ?: emptyList()

                characterDao.insertCharacters(characters.map {
                    CharacterEntity(
                        id = it.id,
                        name = it.name,
                        status = it.status,
                        species = it.species,
                        gender = it.gender,
                        image = it.image,
                        location = it.location,
                        origin = it.origin,
                        episode = it.episode,
                    )
                })

                LoadResult.Page(
                    data = characters,
                    prevKey = if (currentPage > 1) currentPage - 1 else null,
                    nextKey = if (data?.info?.next != null) currentPage + 1 else null
                )
            } else {
                LoadResult.Page(
                    data = characterDao.getAllCharacters().firstOrNull()?.map {
                        Character(
                            gender = it.gender,
                            id = it.id,
                            image = it.image,
                            location = it.location,
                            name = it.name,
                            origin = it.origin,
                            species = it.species,
                            status = it.status,
                            episode = it.episode,
                        )
                    } ?: emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Page(
                data = characterDao.getAllCharacters().firstOrNull()?.map {
                    Character(
                        gender = it.gender,
                        id = it.id,
                        image = it.image,
                        location = it.location,
                        name = it.name,
                        origin = it.origin,
                        species = it.species,
                        status = it.status,
                        episode = it.episode,
                    )
                } ?: emptyList(),
                prevKey = null,
                nextKey = null
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val closestPage = state.closestPageToPosition(anchorPosition)
            closestPage?.prevKey?.plus(1) ?: closestPage?.nextKey?.minus(1)
        }
    }
}