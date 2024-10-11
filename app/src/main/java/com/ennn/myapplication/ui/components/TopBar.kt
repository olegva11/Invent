package com.ennn.myapplication.ui.components

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ennn.myapplication.navigation.ScreenRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicTopAppBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    TopAppBar(
        title = {
            Log.i("route", currentRoute.toString())
            Text(
                text = when (currentRoute) {
                    "LoginScreen" -> "Авторизація"
                    "InventMenuScreen" -> "Документи"
                    "InventDetailedScreen" -> "Деталі"
                    "SettingScreen" -> "Налаштування"
                    else -> ""
                },
                fontSize = 20.sp,
                color = Color.White
            )
        },
        actions = {
            when (currentRoute) {
                "LoginScreen" -> IconButton(onClick = { navController.navigate(ScreenRoute.SettingScreen.route) }) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        tint = Color.White,
                        contentDescription = "Localized description"
                    )
                }

                "InventMenuScreen" -> {}
                "InventDetailedScreen" -> {}
                "SettingScreen" -> {
                    IconButton(onClick = { navController.navigate(ScreenRoute.SettingScreen.route) }) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            tint = Color.White,
                            contentDescription = "Localized description"
                        )
                    }
                }

                else -> {}
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFFA000)),
        navigationIcon = {
            IconButton(onClick = {
                if (navController.previousBackStackEntry != null) {
                    navController.popBackStack()
                }
            }) {
                if (navController.previousBackStackEntry != null)
                    Icon(
                        Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Назад",
                        tint = Color.White
                    )
            }
        }
    )
}