package com.guilhermereisapps.rickandmortykotlin.presentation.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.guilhermereisapps.rickandmortykotlin.presentation.ui.screen.CharactersListScreen
import com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.characterslistviewmodel.ICharactersListViewModel
import com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.filtersviewmodel.IFiltersViewModel
import com.guilhermereisapps.rickandmortykotlin.utils.FakeCharactersListViewModel
import com.guilhermereisapps.rickandmortykotlin.utils.FakeFiltersViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharactersListScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var viewModel: ICharactersListViewModel
    private lateinit var filtersViewModel: IFiltersViewModel

    @Before
    fun setUp() {
        viewModel = FakeCharactersListViewModel()
        filtersViewModel = FakeFiltersViewModel()
    }

    @Test
    fun testCharactersListScreen_DisplayedCorrectly() {
        composeTestRule.setContent {
            CharactersListScreen(
                charactersListViewModel = viewModel,
                filtersViewModel = filtersViewModel,
                onCharacterClick = {},
            )
        }

        // Verifica se a lista de personagens é exibida
        composeTestRule.onNodeWithText("Personagens").assertIsDisplayed()
        composeTestRule.onNodeWithText("Rick Sanchez").assertIsDisplayed()

        // Verifica se pelo menos um item da lista foi renderizado
        composeTestRule.onNode(hasScrollAction()).assertExists()
    }

    @Test
    fun testClickOnCharacter_NavigatesToDetailsScreen() {
        composeTestRule.setContent {
            CharactersListScreen(
                charactersListViewModel = viewModel,
                filtersViewModel = filtersViewModel,
                onCharacterClick = { id ->
                    assert(id == 1)
                },
            )
        }

        // Simula o clique no primeiro personagem da lista
        composeTestRule.onAllNodes(hasScrollAction())[0].performClick()
    }
}
