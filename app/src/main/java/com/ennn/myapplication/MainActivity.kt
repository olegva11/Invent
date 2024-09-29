package com.ennn.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.ennn.myapplication.ui.theme.InventTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            InventTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    InventApp(
                        innerPadding
                    )
                }
            }
        }
    }
}

/*
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    InventTheme {
        Login(navController = navController)
    }
}*/
