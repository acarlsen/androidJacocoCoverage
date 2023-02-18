package com.github.acarlsen.android.coverage.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBarLayout(
    menuItems: List<NavigationBarItemData>,
    destination: NavDestination?,
    onMenuItemSelected: (String) -> Unit,
    content: @Composable (innerPadding: PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                menuItems.forEach { item ->
                    val isSelected = destination?.hierarchy?.any { it.route == item.route } ?: false
                    NavigationBarItem(
                        icon = {
                            Icon(imageVector = item.icon, contentDescription = item.name)
                        },
                        label = {
                            Text(
                                text = item.name,
                                style = MaterialTheme.typography.labelSmall,
                                maxLines = 1,
                                textAlign = TextAlign.Center,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        selected = isSelected,
                        onClick = { onMenuItemSelected(item.route) }
                    )
                }
            }
        }
    ) { innerPadding ->
        content(innerPadding)
    }
}
