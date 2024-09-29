package com.ennn.myapplication.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ennn.myapplication.ui.screens.InventDetailedScreen
import com.ennn.myapplication.ui.screens.InventMenuScreen
import com.ennn.myapplication.ui.screens.LoginScreen

@Composable
fun Navigation(navController: NavHostController, innerPadding: PaddingValues) {

    NavHost(
        navController = navController,
        startDestination = ScreenRoute.LoginScreen.route
    )
    {
        composable(ScreenRoute.LoginScreen.route) {
            LoginScreen(navController = navController, innerPadding = innerPadding)
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
    }
}