package com.guilhermereisapps.rickandmortykotlin.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guilhermereisapps.rickandmortykotlin.presentation.ui.theme.RickAndMortyKotlinTheme

@Composable
fun HorizontalButtonSelection(
    options: List<String>,
    selectedOption: String?,
    onOptionSelected: (String?) -> Unit,
    isSelectable: Boolean = true
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(options) { option ->
            val isSelected = selectedOption == option

            Button(
                onClick = {
                    if (isSelectable) {
                        onOptionSelected(if (isSelected) null else option)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                ),
                shape = RoundedCornerShape(12.dp),
                enabled = isSelectable
            ) {
                Text(text = option)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HorizontalButtonSelectionPreview() {
    RickAndMortyKotlinTheme {
        HorizontalButtonSelection(
            options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5"),
            selectedOption = "Option 2",
            onOptionSelected = {}
        )
    }
}
