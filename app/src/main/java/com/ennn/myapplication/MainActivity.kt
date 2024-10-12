package com.ennn.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.ennn.myapplication.navigation.Navigation
import com.ennn.myapplication.ui.SharedViewModel
import com.ennn.myapplication.ui.components.DynamicTopAppBar
import com.ennn.myapplication.ui.theme.InventTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val viewModel: SharedViewModel = hiltViewModel()

            val navController = rememberNavController()
            InventTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        DynamicTopAppBar(navController, viewModel)
                    }) { innerPadding ->
                    Navigation(navController, viewModel, innerPadding)
                }
            }
        }
    }
}

