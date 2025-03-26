package com.guilhermereisapps.rickandmortykotlin.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.guilhermereisapps.rickandmortykotlin.R
import com.guilhermereisapps.rickandmortykotlin.presentation.ui.theme.RickAndMortyKotlinTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onLoaded: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        AsyncImage(
            model = R.drawable.rick_and_morty_logo,
            contentDescription = "Rick and Morty logo",
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.Center)
        )
    }
    LaunchedEffect(true) {
        delay(5000)
        onLoaded()
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    RickAndMortyKotlinTheme {
        SplashScreen {  }
    }
}
