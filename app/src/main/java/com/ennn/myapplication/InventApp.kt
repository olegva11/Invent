package com.ennn.myapplication

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.ennn.myapplication.navigation.Navigation

@Composable
fun InventApp(navController: NavHostController, modifier: PaddingValues)
{
    Navigation(navController, modifier)
}