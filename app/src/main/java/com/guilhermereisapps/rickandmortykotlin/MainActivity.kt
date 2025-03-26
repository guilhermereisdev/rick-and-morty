package com.guilhermereisapps.rickandmortykotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.guilhermereisapps.rickandmortykotlin.presentation.ui.NavGraph
import com.guilhermereisapps.rickandmortykotlin.presentation.ui.theme.RickAndMortyKotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyKotlinTheme {
                NavGraph()
            }
        }
    }
}
