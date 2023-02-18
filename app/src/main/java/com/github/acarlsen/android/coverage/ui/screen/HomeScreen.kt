package com.github.acarlsen.android.coverage.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.acarlsen.android.coverage.ui.theme.AndroidJacocoCoverageTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    HomeScreenComposable(
        uiState = uiState,
        toggleGreeting = viewModel::toggleGreeting
    )
}

@Composable
fun HomeScreenComposable(
    uiState: HomeUiState,
    toggleGreeting: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = uiState.greeting)
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedButton(onClick = toggleGreeting) {
            Text(text = "Toggle greeting")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenComposablePreview() {
    AndroidJacocoCoverageTheme {
        HomeScreenComposable(
            uiState = HomeUiState(
                greeting = "Preview greeting"
            ),
            toggleGreeting = {}
        )
    }
}
