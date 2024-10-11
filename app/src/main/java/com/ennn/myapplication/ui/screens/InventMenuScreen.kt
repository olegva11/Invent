package com.ennn.myapplication.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ennn.myapplication.navigation.ScreenRoute


@Composable
fun InventMenuScreen(innerPadding: PaddingValues, navController: NavHostController) {
    val itemsButtonText = listOf(
        "Інвентаризація",
        "Замовлення клієнта",
        "Повернення від клієнтів",
        "Надходження товарів"
    )

    Box(modifier = Modifier.padding(innerPadding))
    {
        LazyColumn {
            items(itemsButtonText) {
                ItemButton(it, 0) { navController.navigate(ScreenRoute.InventDetailedScreen.route) }
            }
        }
    }

}

@Composable
fun ItemButton(itemText: String, itemCount: Int, onClick: () -> Unit) {
    Card(modifier = Modifier.padding(5.dp).clickable { onClick() }) {
        Row(modifier = Modifier.padding(10.dp)) {
            Text(modifier = Modifier.weight(2f), text = itemText)
            Text(
                modifier = Modifier.weight(1f),
                text = itemCount.toString(),
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun InventMenuScreenPreview() {
    val navController = rememberNavController()  // Створюємо NavController для прев'ю
    val paddingValues = PaddingValues(16.dp)      // Задаємо стандартний PaddingValues для прев'ю

    InventMenuScreen(innerPadding = paddingValues, navController = navController)
}