package com.github.acarlsen.android.coverage.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.github.acarlsen.android.coverage.ui.navigation.NavigationBarLayout
import com.github.acarlsen.android.coverage.ui.navigation.NavigationHost
import com.github.acarlsen.android.coverage.ui.navigation.NavigationRailLayout
import com.github.acarlsen.android.coverage.ui.navigation.getNavigationBarItems
import com.github.acarlsen.android.coverage.ui.theme.AndroidJacocoCoverageTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

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
            NavigationBarLayout(
                menuItems = getNavigationBarItems(),
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
                menuItems = getNavigationBarItems(),
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
