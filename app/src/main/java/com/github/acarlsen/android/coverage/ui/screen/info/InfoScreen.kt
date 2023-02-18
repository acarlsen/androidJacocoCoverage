package com.github.acarlsen.android.coverage.ui.screen.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.acarlsen.android.coverage.ui.theme.AndroidJacocoCoverageTheme

@Composable
fun InfoScreen() {
    InfoScreenComposable()
}

@Composable
fun InfoScreenComposable() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Jacoco demo app v. 1.0")
    }
}

@Preview(showBackground = true)
@Composable
fun InfoScreenComposablePreview() {
    AndroidJacocoCoverageTheme {
        InfoScreenComposable()
    }
}
