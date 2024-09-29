package com.ennn.myapplication.navigation

sealed class ScreenRoute(val route: String) {
    object LoginScreen : ScreenRoute("LoginScreen")
    object InventMenuScreen : ScreenRoute("InventMenuScreen")
    object InventDetailedScreen : ScreenRoute("InventDetailedScreen")
}