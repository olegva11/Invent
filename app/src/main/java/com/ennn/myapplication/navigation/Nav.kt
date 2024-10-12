package com.ennn.myapplication.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ennn.myapplication.ui.SharedViewModel
import com.ennn.myapplication.ui.screens.InventDetailedScreen
import com.ennn.myapplication.ui.screens.InventMenuScreen
import com.ennn.myapplication.ui.screens.LoginScreen
import com.ennn.myapplication.ui.screens.SettingScreen

@Composable
fun Navigation(
    navController: NavHostController,
    viewModel: SharedViewModel,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.LoginScreen.route
    )
    {
        composable(ScreenRoute.LoginScreen.route) {
            LoginScreen(navController = navController, viewModel = viewModel, innerPadding = innerPadding)
        }

        composable(
            ScreenRoute.InventMenuScreen.route
        ) {
            InventMenuScreen(navController = navController, innerPadding = innerPadding)
        }

        composable(
            ScreenRoute.InventDetailedScreen.route
        ) {
            InventDetailedScreen(innerPadding)
        }

        composable(
            ScreenRoute.SettingScreen.route
        ) {
            SettingScreen(innerPadding, viewModel)
        }
    }
}