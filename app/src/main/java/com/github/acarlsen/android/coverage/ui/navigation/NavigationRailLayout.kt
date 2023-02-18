package com.github.acarlsen.android.coverage.ui.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.zIndex
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy

@Composable
fun NavigationRailLayout(
    menuItems: List<NavigationBarItemData>,
    destination: NavDestination?,
    onMenuItemSelected: (String) -> Unit,
    content: @Composable () -> Unit
) {
    Row(modifier = Modifier.fillMaxSize()) {
        NavigationRail(
            modifier = Modifier.zIndex(1f)
        ) {
            menuItems.forEach { item ->
                val isSelected = destination?.hierarchy?.any { it.route == item.route } ?: false
                NavigationRailItem(
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
        content()
    }
}
