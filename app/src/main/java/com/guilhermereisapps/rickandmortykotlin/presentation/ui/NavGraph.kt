package com.guilhermereisapps.rickandmortykotlin.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.guilhermereisapps.rickandmortykotlin.presentation.ui.screen.CharacterDetailsScreen
import com.guilhermereisapps.rickandmortykotlin.presentation.ui.screen.CharactersListScreen
import com.guilhermereisapps.rickandmortykotlin.presentation.ui.screen.SplashScreen
import com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.characterdetailsviewmodel.CharacterDetailsViewModel
import com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.characterslistviewmodel.CharactersListViewModel
import com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.filtersviewmodel.FiltersViewModel
import org.koin.androidx.compose.koinViewModel

sealed class Screen(val route: String) {
    data object Splash : Screen("splashScreen")
    data object CharactersListScreen : Screen("charactersListScreen")
    data object CharacterDetailsScreen : Screen("characterDetailsScreen")
}

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val charactersListViewModel: CharactersListViewModel = koinViewModel()
    val characterDetailsViewModel: CharacterDetailsViewModel = koinViewModel()
    val filtersViewModel: FiltersViewModel = koinViewModel()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onLoaded = {
                    navController.navigate(Screen.CharactersListScreen.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.CharactersListScreen.route) {
            CharactersListScreen(
                charactersListViewModel = charactersListViewModel,
                filtersViewModel = filtersViewModel,
                onCharacterClick = { characterId ->
                    navController.navigate("${Screen.CharacterDetailsScreen.route}/$characterId")
                })
        }

        composable("${Screen.CharacterDetailsScreen.route}/{characterId}") { backStackEntry ->
            val characterId =
                backStackEntry.arguments?.getString("characterId") ?: return@composable

            CharacterDetailsScreen(
                characterId = characterId.toInt(),
                characterDetailsViewModel = characterDetailsViewModel
            )
        }
    }
}
