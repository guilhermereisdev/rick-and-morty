package com.guilhermereisapps.rickandmortykotlin.domain.usecase

import androidx.paging.PagingData
import com.guilhermereisapps.rickandmortykotlin.data.model.Filters
import com.guilhermereisapps.rickandmortykotlin.data.model.character.Character
import com.guilhermereisapps.rickandmortykotlin.data.model.character.Location
import com.guilhermereisapps.rickandmortykotlin.data.model.character.Origin
import com.guilhermereisapps.rickandmortykotlin.data.model.util.ResultState
import com.guilhermereisapps.rickandmortykotlin.domain.repository.ICharactersRepository
import io.mockk.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CharactersUseCaseTest {

    private lateinit var useCase: CharactersUseCaseImpl
    private val repository: ICharactersRepository = mockk()

    @Before
    fun setUp() {
        useCase = CharactersUseCaseImpl(repository)
    }

    @Test
    fun `verifica se getAllCharacters chama o repository corretamente`() = runTest {
        val filters = Filters(name = "Rick")
        coEvery { repository.getAllCharacters(filters) } returns flowOf(PagingData.from(emptyList()))

        val result = useCase.getAllCharacters(filters)

        coVerify { repository.getAllCharacters(filters) }
        assertNotNull(result)
    }

    @Test
    fun `verifica se getCharacterById retorna um personagem corretamente`() = runTest {
        val character = Character(
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
        coEvery { repository.getCharacterById(1) } returns flowOf(ResultState(data = character))

        val result = useCase.getCharacterById(1)

        coVerify { repository.getCharacterById(1) }
        assertNotNull(result)
    }
}
