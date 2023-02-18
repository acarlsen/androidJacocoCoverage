package com.github.acarlsen.android.coverage.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.github.acarlsen.android.coverage.domain.models.CocktailModel
import com.github.acarlsen.android.coverage.ui.theme.AndroidJacocoCoverageTheme
import com.github.acarlsen.android.coverage.ui.util.ViewModelUiState

@Composable
fun CocktailScreen(
    viewModel: CocktailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    CocktailScreenComposable(
        uiState,
        clickNewCocktail = viewModel::getNewCocktail
    )
}

@Composable
fun CocktailScreenComposable(uiState: ViewModelUiState<CocktailModel>, clickNewCocktail: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (uiState) {
            is ViewModelUiState.Error -> {
                Text(text = "Error", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = uiState.message)
                Spacer(modifier = Modifier.height(32.dp))
                Button(onClick = clickNewCocktail) {
                    Text(text = "Retry")
                }
            }

            is ViewModelUiState.Loading -> {
                Text(text = "Loading", style = MaterialTheme.typography.titleMedium)
            }

            is ViewModelUiState.Success -> {
                Text(text = uiState.value.name, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = uiState.value.instructions)
                uiState.value.image?.let { image ->
                    Spacer(modifier = Modifier.height(16.dp))
                    AsyncImage(
                        modifier = Modifier.height(100.dp),
                        model = image,
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                Button(onClick = clickNewCocktail) {
                    Text(text = "Get new cocktail")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CocktailScreenComposablePreview() {
    AndroidJacocoCoverageTheme {
        CocktailScreenComposable(
            uiState = ViewModelUiState.Success(
                CocktailModel(
                    id = "2",
                    name = "Margarita",
                    instructions = "Rub the rim of the glass with the lime slice to make " +
                        "the salt stick to it. Take care to moisten only the outer rim and " +
                        "sprinkle the salt on it. The salt should present to the lips of " +
                        "the imbiber and never mix into the cocktail. Shake the other " +
                        "ingredients with ice, then carefully pour into the glass.",
                    image = "https://commons.wikimedia.org/wiki/File:Klassiche_Margarita.jpg"
                )
            ),
            clickNewCocktail = {}
        )
    }
}
