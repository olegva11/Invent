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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ennn.myapplication.ui.SharedViewModel

@Composable
fun SettingScreen(innerPadding: PaddingValues, viewModel: SharedViewModel) {
    val ipAddress = viewModel.ipAddress.collectAsState()
    val portAddress = viewModel.portAddress.collectAsState()

    val ipAddressField = remember { mutableStateOf(ipAddress.value) }
    val portAddressField = remember { mutableStateOf(portAddress.value) }

    val isErrorIp = remember { mutableStateOf(false) }
    val isErrorPort = remember { mutableStateOf(false) }

    val ipRegex =
        Regex("^(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])(\\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])){3}$")
    val portRegex = Regex("^([0-9]{1,5})\$")

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.padding(top = 20.dp, start = 10.dp, end = 10.dp)) {
            Text(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp), text = "Server ip")
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp, color = if (isErrorIp.value) {
                            Color.Red
                        } else {
                            Color.White
                        }, shape = RoundedCornerShape(20.dp)
                    )
                    .clip(RoundedCornerShape(20.dp)),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                value = ipAddressField.value,
                isError = isErrorIp.value,
                onValueChange = {
                    isErrorIp.value = !ipRegex.matches(it) && it.isNotEmpty()
                    viewModel.isError(isErrorIp.value, isErrorPort.value)

                    ipAddressField.value = it
                    viewModel.ipAddressField = it
                }
            )
            Text(
                text = if (isErrorIp.value) {
                    "Invalid ip"
                } else {
                    " "
                },
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(Modifier.height(20.dp))
            Text(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp), text = "Port")
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp, color = if (isErrorPort.value) {
                            Color.Red
                        } else {
                            Color.White
                        }, shape = RoundedCornerShape(20.dp)
                    )
                    .clip(RoundedCornerShape(20.dp)),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                value = portAddressField.value,
                isError = isErrorPort.value,
                onValueChange = {
                    isErrorPort.value = !portRegex.matches(it) && it.isNotEmpty()
                    viewModel.isError(isErrorIp.value, isErrorPort.value)

                    portAddressField.value = it
                    viewModel.portAddressField = it
                }
            )
            Text(
                text = if (isErrorPort.value) {
                    "Invalid port"
                } else {
                    " "
                },
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}