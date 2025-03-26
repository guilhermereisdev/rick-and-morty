package com.guilhermereisapps.rickandmortykotlin.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guilhermereisapps.rickandmortykotlin.data.model.Filters
import com.guilhermereisapps.rickandmortykotlin.presentation.ui.theme.RickAndMortyKotlinTheme
import com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.filtersviewmodel.FiltersViewModelPreview
import com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.filtersviewmodel.IFiltersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    filtersViewModel: IFiltersViewModel,
    onApplyFilters: (filters: Filters) -> Unit,
    onDismiss: () -> Unit
) {
    val filtersState by filtersViewModel.filters.collectAsState()
    val sheetState = rememberModalBottomSheetState()
    var name by remember { mutableStateOf(filtersState.name ?: "") }
    var status by remember { mutableStateOf(filtersState.status) }
    var species by remember { mutableStateOf(filtersState.species ?: "") }
    var gender by remember { mutableStateOf(filtersState.gender) }

    LaunchedEffect(Unit) {
        sheetState.expand()
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        modifier = Modifier.fillMaxWidth(),
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Filtrar Personagens",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.clickable {
                        name = ""
                        status = null
                        species = ""
                        gender = null
                    },
                    text = "limpar filtros",
                    style = MaterialTheme.typography.titleMedium,
                    textDecoration = TextDecoration.Underline,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nome") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )

                OutlinedTextField(
                    value = species,
                    onValueChange = { species = it },
                    label = { Text("Espécie") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Status", style = MaterialTheme.typography.titleMedium)
            HorizontalButtonSelection(
                options = listOf("Alive", "Dead", "Unknown"),
                selectedOption = status,
                onOptionSelected = { newStatus -> status = newStatus }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Gênero", style = MaterialTheme.typography.titleMedium)
            HorizontalButtonSelection(
                options = listOf("Female", "Male", "Genderless", "Unknown"),
                selectedOption = gender,
                onOptionSelected = { newGender -> gender = newGender }
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    val newFilters = Filters(name, status, species, gender)
                    filtersViewModel.updateFilters(newFilters)
                    onApplyFilters(newFilters)
                    onDismiss()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Pesquisar")
            }
        }
    }
}

@Preview
@Composable
private fun FilterBottomSheetPreview() {
    RickAndMortyKotlinTheme {
        FilterBottomSheet(
            filtersViewModel = FiltersViewModelPreview(),
            onApplyFilters = {},
            onDismiss = {},
        )
    }
}
