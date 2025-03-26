package com.guilhermereisapps.rickandmortykotlin.presentation.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.guilhermereisapps.rickandmortykotlin.data.model.character.Character
import com.guilhermereisapps.rickandmortykotlin.presentation.ui.component.ErrorView
import com.guilhermereisapps.rickandmortykotlin.presentation.ui.component.HorizontalButtonSelection
import com.guilhermereisapps.rickandmortykotlin.presentation.ui.component.LoadingIndicator
import com.guilhermereisapps.rickandmortykotlin.presentation.ui.theme.RickAndMortyKotlinTheme
import com.guilhermereisapps.rickandmortykotlin.presentation.util.extractEpisodeNumber
import com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.characterdetailsviewmodel.CharacterDetailsViewModelPreview
import com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.characterdetailsviewmodel.ICharacterDetailsViewModel

@Composable
fun CharacterDetailsScreen(
    characterId: Int,
    characterDetailsViewModel: ICharacterDetailsViewModel,
) {
    val charactersState by characterDetailsViewModel.characterState.collectAsState()
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    LaunchedEffect(characterId) {
        characterDetailsViewModel.fetchCharacterDetails(characterId)
    }

    Scaffold { contentPadding ->
        when {
            charactersState.isLoading -> {
                LoadingIndicator()
            }

            charactersState.error != null -> {
                ErrorView(message = charactersState.error ?: "Erro desconhecido")
            }

            else -> {
                val character = charactersState.data
                val imageModifier = Modifier
                    .fillMaxWidth(if (isLandscape) 0.4f else 1f)

                val detailsModifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)

                if (isLandscape) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(contentPadding)
                            .consumeWindowInsets(contentPadding)
                            .background(Color(0xFF1E1E1E)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CharacterImage(character?.image, imageModifier)
                        CharacterDetails(character, detailsModifier)
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(contentPadding)
                            .consumeWindowInsets(contentPadding),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CharacterImage(character?.image, imageModifier)
                        Spacer(modifier = Modifier.height(16.dp))
                        CharacterDetails(character, detailsModifier)
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterImage(imageUrl: String?, modifier: Modifier) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "Character Image",
        modifier = modifier
            .aspectRatio(1f),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun CharacterDetails(character: Character?, modifier: Modifier) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = character?.name ?: "Desconhecido",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(8.dp))

        CharacterDetailItem(label = "Espécie", value = character?.species)
        CharacterDetailItem(label = "Gênero", value = character?.gender)
        CharacterDetailItem(label = "Status", value = character?.status)
        CharacterDetailItem(label = "Origem", value = character?.origin?.name)
        CharacterDetailItem(label = "Localização", value = character?.location?.name)
        if (character?.episode?.isNotEmpty() == true) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(end = 8.dp),
                    text = "Episódios:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                HorizontalButtonSelection(
                    character.episode.map { it.extractEpisodeNumber() },
                    selectedOption = null,
                    onOptionSelected = { },
                    isSelectable = false
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun CharacterDetailItem(label: String, value: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${label}:",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            text = value ?: "Desconhecido",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.End,
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun CharacterDetailScreenPreview() {
    RickAndMortyKotlinTheme {
        CharacterDetailsScreen(
            characterId = 1,
            characterDetailsViewModel = CharacterDetailsViewModelPreview()
        )
    }
}
