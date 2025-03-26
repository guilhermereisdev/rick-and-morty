package com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel

import com.guilhermereisapps.rickandmortykotlin.data.model.character.Character
import com.guilhermereisapps.rickandmortykotlin.data.model.character.Location
import com.guilhermereisapps.rickandmortykotlin.data.model.character.Origin
import com.guilhermereisapps.rickandmortykotlin.data.model.util.ResultState
import com.guilhermereisapps.rickandmortykotlin.domain.usecase.ICharactersUseCase
import com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.characterdetailsviewmodel.CharacterDetailsViewModel
import com.guilhermereisapps.rickandmortykotlin.utils.TestCoroutineRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterDetailsViewModelTest {

    @get:Rule
    val dispatcherRule = TestCoroutineRule()

    private lateinit var viewModel: CharacterDetailsViewModel
    private val charactersUseCase: ICharactersUseCase = mockk()

    @Before
    fun setUp() {
        coEvery { charactersUseCase.getCharacterById(any()) } returns flowOf(
            ResultState(
                data = Character(
                    created = "",
                    episode = emptyList(),
                    gender = "",
                    id = 1,
                    image = "",
                    location = Location(name = "", url = ""),
                    name = "Rick",
                    origin = Origin(name = "", url = ""),
                    species = "Human",
                    status = "Alive",
                    type = "",
                    url = ""
                )
            )
        )

        viewModel = CharacterDetailsViewModel(charactersUseCase)
    }

    @Test
    fun `verifica se os detalhes do personagem são carregados corretamente`() = runTest {
        viewModel.fetchCharacterDetails(1)

        val result = viewModel.characterState.value
        assertNotNull(result.data)
        assertEquals("Rick", result.data?.name)
    }
}