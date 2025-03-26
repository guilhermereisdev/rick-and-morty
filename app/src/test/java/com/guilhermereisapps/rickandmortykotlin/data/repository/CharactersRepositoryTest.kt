package com.guilhermereisapps.rickandmortykotlin.data.repository

import com.guilhermereisapps.rickandmortykotlin.data.local.dao.CharacterDao
import com.guilhermereisapps.rickandmortykotlin.data.local.entity.CharacterEntity
import com.guilhermereisapps.rickandmortykotlin.data.model.character.Location
import com.guilhermereisapps.rickandmortykotlin.data.model.character.Origin
import com.guilhermereisapps.rickandmortykotlin.data.remote.RemoteService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class CharactersRepositoryTest {

    private lateinit var repository: CharactersRepositoryImpl
    private val apiService: RemoteService = mockk()
    private val characterDao: CharacterDao = mockk()

    @Before
    fun setUp() {
        repository = CharactersRepositoryImpl(apiService, characterDao)
    }

    @Test
    fun `verifica se os personagens são carregados do banco local antes da API`() = runTest {
        coEvery { characterDao.getCharacterById(1) } returns flowOf(
            CharacterEntity(
                episode = emptyList(),
                gender = "",
                id = 1,
                image = "",
                location = Location(name = "", url = ""),
                name = "Rick",
                origin = Origin(name = "", url = ""),
                species = "Human",
                status = "Alive",
            )
        )

        val result = repository.getCharacterById(1).first()

        assertNotNull(result.data)
        assertEquals("Rick", result.data?.name)
    }
}