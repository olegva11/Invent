package com.ennn.myapplication.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SettingScreen(innerPadding: PaddingValues) {
    var ipAddress by remember { mutableStateOf("") }
    var portAddress by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.padding(top = 20.dp, start = 10.dp, end = 10.dp)) {
            Text(modifier = Modifier.fillMaxWidth().padding(start = 10.dp), text = "Server ip")
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp)),
                value = ipAddress,
                onValueChange = { ipAddress = it }
            )
            Spacer(Modifier.height(20.dp))
            Text(modifier = Modifier.fillMaxWidth().padding(start = 10.dp), text = "Port")
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp)),
                value = portAddress,
                onValueChange = { portAddress = it }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Preview() {
    SettingScreen(innerPadding = PaddingValues(16.dp))
}