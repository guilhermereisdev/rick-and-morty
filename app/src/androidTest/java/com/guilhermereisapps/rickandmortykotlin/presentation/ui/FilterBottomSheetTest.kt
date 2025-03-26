package com.guilhermereisapps.rickandmortykotlin.presentation.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.guilhermereisapps.rickandmortykotlin.presentation.ui.component.FilterBottomSheet
import com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.filtersviewmodel.IFiltersViewModel
import com.guilhermereisapps.rickandmortykotlin.utils.FakeFiltersViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FilterBottomSheetTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var filtersViewModel: IFiltersViewModel

    @Before
    fun setUp() {
        filtersViewModel = FakeFiltersViewModel()
    }

    @Test
    fun testFilterBottomSheet_DisplayedCorrectly() {
        composeTestRule.setContent {
            FilterBottomSheet(
                filtersViewModel = filtersViewModel,
                onApplyFilters = {},
                onDismiss = {})
        }

        // Verifica se o título do bottom sheet é exibido
        composeTestRule.onNodeWithText("Filtrar Personagens").assertIsDisplayed()

        // Verifica se os campos de filtro estão na tela
        composeTestRule.onNodeWithText("Nome").assertExists()
        composeTestRule.onNodeWithText("Espécie").assertExists()
        composeTestRule.onNodeWithText("Status").assertExists()
        composeTestRule.onNodeWithText("Gênero").assertExists()
    }

    @Test
    fun testApplyFiltersButton_Click() {
        composeTestRule.setContent {
            FilterBottomSheet(
                filtersViewModel = filtersViewModel,
                onApplyFilters = {
                    assert(it.name == "Rick")
                },
                onDismiss = {}
            )
        }

        // Digita "Rick" no campo Nome
        composeTestRule.onNodeWithText("Nome").performTextInput("Rick")

        // Clica no botão "Aplicar Filtros"
        composeTestRule.onNodeWithText("Pesquisar").performClick()
    }
}
