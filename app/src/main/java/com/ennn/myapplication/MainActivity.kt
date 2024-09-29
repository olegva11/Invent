package com.ennn.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ennn.myapplication.ui.theme.InventTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            InventTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        DynamicTopAppBar(navController)
                    }) { innerPadding ->
                    InventApp(
                        navController, innerPadding
                    )
                }
            }
        }
    }
}


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
                    else -> "Документи"
                },
                fontSize = 20.sp,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFFA000)) // Помаранчевий колір
        ,
        navigationIcon = {
            IconButton(onClick = {
                if (navController.previousBackStackEntry != null) {
                    navController.popBackStack() // Навігація назад
                }
            }) {
                if(navController.previousBackStackEntry != null)
                Icon(Icons.Default.ArrowBack, contentDescription = "Назад", tint = Color.White)
            }
        }
    )
}