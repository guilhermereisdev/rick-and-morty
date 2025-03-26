package com.guilhermereisapps.rickandmortykotlin.presentation.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.guilhermereisapps.rickandmortykotlin.presentation.ui.screen.CharacterDetailsScreen
import com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.characterdetailsviewmodel.ICharacterDetailsViewModel
import com.guilhermereisapps.rickandmortykotlin.utils.FakeCharacterDetailsViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterDetailsScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var viewModel: ICharacterDetailsViewModel

    @Before
    fun setUp() {
        viewModel = FakeCharacterDetailsViewModel()
    }

    @Test
    fun testCharacterDetailsScreen_DisplaysCharacterInfo() {
        composeTestRule.setContent {
            CharacterDetailsScreen(characterId = 1, characterDetailsViewModel = viewModel)
        }

        // 🔹 Verifica se o nome do personagem é exibido
        composeTestRule.onNodeWithText("Rick Sanchez").assertIsDisplayed()

        // 🔹 Verifica se os detalhes do personagem estão na tela
        composeTestRule.onNodeWithText("Human").assertIsDisplayed()
        composeTestRule.onNodeWithText("Male").assertIsDisplayed()
    }
}
