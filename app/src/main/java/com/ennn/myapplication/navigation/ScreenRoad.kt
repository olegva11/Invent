package com.ennn.myapplication.navigation

sealed class ScreenRoute(val route: String) {
    data object LoginScreen : ScreenRoute("LoginScreen")
    data object InventMenuScreen : ScreenRoute("InventMenuScreen")
    data object InventDetailedScreen : ScreenRoute("InventDetailedScreen")
    data object SettingScreen : ScreenRoute("SettingScreen")
}