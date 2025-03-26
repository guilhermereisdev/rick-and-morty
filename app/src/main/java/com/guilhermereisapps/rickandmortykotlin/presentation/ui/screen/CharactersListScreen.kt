package com.guilhermereisapps.rickandmortykotlin.presentation.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.guilhermereisapps.rickandmortykotlin.R
import com.guilhermereisapps.rickandmortykotlin.presentation.ui.component.CharacterCard
import com.guilhermereisapps.rickandmortykotlin.presentation.ui.component.FilterBottomSheet
import com.guilhermereisapps.rickandmortykotlin.presentation.ui.component.LoadingIndicator
import com.guilhermereisapps.rickandmortykotlin.presentation.ui.theme.RickAndMortyKotlinTheme
import com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.characterslistviewmodel.CharactersListViewModelPreview
import com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.characterslistviewmodel.ICharactersListViewModel
import com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.filtersviewmodel.FiltersViewModelPreview
import com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.filtersviewmodel.IFiltersViewModel

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CharactersListScreen(
    charactersListViewModel: ICharactersListViewModel,
    filtersViewModel: IFiltersViewModel,
    onCharacterClick: (Int) -> Unit
) {
    val characters = charactersListViewModel.charactersPaging.collectAsLazyPagingItems()
//    val pullRefreshState = rememberPullRefreshState(
//        refreshing = charactersState.isLoading,
//        onRefresh = { charactersListViewModel.refreshCharacters() }
//    )
    var expandedMenu by remember { mutableStateOf(false) }
    var isHighContrastEnabled by remember { mutableStateOf(false) }
    var isFilterSheetOpen by remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val columns = if (isLandscape) 3 else 1

    RickAndMortyKotlinTheme(
        darkTheme = isSystemInDarkTheme(),
        isHighContrast = isHighContrastEnabled
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Personagens",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    actions = {
                        IconButton(onClick = { isFilterSheetOpen = true }) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(R.drawable.ic_filter),
                                contentDescription = "Filtrar",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                        IconButton(onClick = { expandedMenu = !expandedMenu }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Configurações",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                        DropdownMenu(
                            expanded = expandedMenu,
                            onDismissRequest = { expandedMenu = false },
                            modifier = Modifier.background(MaterialTheme.colorScheme.surface),
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        "Alto Contraste",
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                },
                                onClick = {
                                    isHighContrastEnabled = !isHighContrastEnabled
                                    expandedMenu = false
                                }
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                )
            }
        ) { contentPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
//                    .pullRefresh(pullRefreshState)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(contentPadding)
                    .consumeWindowInsets(contentPadding)
            ) {
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = GridCells.Fixed(columns),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(characters.itemCount) { index ->
                        val character = characters[index]
                        character?.let {
                            CharacterCard(
                                character = it,
                                onClick = {
                                    it.id?.let { id -> onCharacterClick(id) }
                                }
                            )
                        }
                    }

                    characters.apply {
                        when {
                            loadState.append is LoadState.Loading -> {
                                item {
                                    LoadingIndicator()
                                }
                            }

                            loadState.refresh is LoadState.Error -> {
                                item {
                                    Text(
                                        text = "Erro ao carregar personagens",
                                        color = Color.Red,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                            }
                        }
                    }
                }

                if (characters.loadState.refresh is LoadState.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        if (isFilterSheetOpen) {
            FilterBottomSheet(
                filtersViewModel = filtersViewModel,
                onApplyFilters = { newFilters ->
                    charactersListViewModel.refreshCharacters(newFilters)
                    isFilterSheetOpen = false
                },
                onDismiss = { isFilterSheetOpen = false }
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun CharactersListScreenPreview() {
    RickAndMortyKotlinTheme {
        CharactersListScreen(
            charactersListViewModel = CharactersListViewModelPreview(),
            filtersViewModel = FiltersViewModelPreview(),
            onCharacterClick = {},
        )
    }
}
