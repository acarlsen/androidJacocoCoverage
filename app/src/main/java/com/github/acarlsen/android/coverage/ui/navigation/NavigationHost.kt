package com.github.acarlsen.android.coverage.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.github.acarlsen.android.coverage.R
import com.github.acarlsen.android.coverage.ui.screen.cocktail.CocktailScreen
import com.github.acarlsen.android.coverage.ui.screen.home.HomeScreen
import com.github.acarlsen.android.coverage.ui.screen.info.InfoScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationHost(
    modifier: Modifier,
    navController: NavHostController,
) {
    AnimatedNavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationRoute.Home.route
    ) {
        composable(NavigationRoute.Home.route) {
            HomeScreen()
        }
        composable(NavigationRoute.Cocktail.route) {
            CocktailScreen()
        }
        composable(NavigationRoute.Info.route) {
            InfoScreen()
        }
    }
}

@Composable
fun getNavigationBarItems(): List<NavigationBarItemData> {
    return listOf(
        NavigationBarItemData(
            route = NavigationRoute.Home.route,
            name = stringResource(id = R.string.menu_home),
            icon = Icons.Filled.Dashboard
        ),
        NavigationBarItemData(
            route = NavigationRoute.Cocktail.route,
            name = stringResource(id = R.string.menu_cocktail),
            icon = Icons.Filled.LocalDrink
        ),
        NavigationBarItemData(
            route = NavigationRoute.Info.route,
            name = stringResource(id = R.string.menu_info),
            icon = Icons.Filled.Folder
        ),
    )
}

data class NavigationBarItemData(
    val route: String,
    val name: String,
    val icon: ImageVector,
)

sealed class NavigationRoute(val route: String) {
    object Home : NavigationRoute("Home")
    object Cocktail : NavigationRoute("Cocktail")
    object Info : NavigationRoute("Info")
}
