package com.ennn.myapplication

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.ennn.myapplication.navigation.Navigation

@Composable
fun InventApp(modifier: PaddingValues)
{
    val navController = rememberNavController()
    Navigation(navController, modifier)
}