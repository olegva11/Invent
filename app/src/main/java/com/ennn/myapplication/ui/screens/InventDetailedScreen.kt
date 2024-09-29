package com.ennn.myapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InventDetailedScreen(innerPadding: PaddingValues)
{
    val listl = listOf("25.09.2024", "26.09.2024")
    Box(modifier = Modifier.padding(innerPadding)){
        LazyColumn {
            items(listl){ item->
                ItemInventDetailed(item)
            }
        }
    }
}

@Composable
fun ItemInventDetailed(date: String)
{
    Card(modifier = Modifier.fillMaxWidth().padding(5.dp)){
        Row(modifier = Modifier.fillMaxWidth().padding(5.dp), horizontalArrangement = Arrangement.SpaceBetween){
            Column(modifier = Modifier) {
                Text("ФО00002921 від $date")
                Text("Контрагент: Чуповський Михайло")
                Text("Склад: Центр")
                Text("Зробіть ТТН для відправки")
            }
            Icon(imageVector = Icons.Filled.MoreVert,"")
        }
    }
}