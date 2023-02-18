package com.github.acarlsen.android.coverage.ui

import NavigationHost
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.zIndex
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.github.acarlsen.android.coverage.ui.theme.AndroidJacocoCoverageTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import getNavigationBarItems

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidJacocoCoverageTheme {
                MainScreen(
                    windowSizeClass = calculateWindowSizeClass(this)
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    windowSizeClass: WindowSizeClass,
) {
    val navController = rememberAnimatedNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentDestination = navBackStackEntry?.destination

    val navHost = remember {
        movableContentOf<PaddingValues> { innerPadding ->
            NavigationHost(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
            )
        }
    }

    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            BottomBarLayout(
                destination = currentDestination,
                onMenuItemSelected = { route ->
                    navController.navigate(route) {
                        // Pop up to the start destination of the graph to avoid building up a large stack of destinations
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when re-selecting the same item
                        launchSingleTop = true
                        // Restore state when re-selecting a previously selected item
                        restoreState = true
                    }
                }
            ) { innerPadding ->
                navHost(innerPadding)
            }
        }

        else -> {
            NavigationRailLayout(
                destination = currentDestination,
                onMenuItemSelected = { route ->
                    navController.navigate(route) {
                        // Pop up to the start destination of the graph to avoid building up a large stack of destinations
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when re-selecting the same item
                        launchSingleTop = true
                        // Restore state when re-selecting a previously selected item
                        restoreState = true
                    }
                }
            ) {
                navHost(PaddingValues())
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBarLayout(
    destination: NavDestination?,
    onMenuItemSelected: (String) -> Unit,
    content: @Composable (innerPadding: PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                getNavigationBarItems().forEach { item ->
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

@Composable
fun NavigationRailLayout(
    destination: NavDestination?,
    onMenuItemSelected: (String) -> Unit,
    content: @Composable () -> Unit
) {
    Row(modifier = Modifier.fillMaxSize()) {
        NavigationRail(
            modifier = Modifier.zIndex(1f)
        ) {
            getNavigationBarItems().forEach { item ->
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
