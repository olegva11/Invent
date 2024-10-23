package com.ennn.myapplication.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ennn.myapplication.ui.components.InputFieldWithError

@Composable
fun SettingScreen(innerPadding: PaddingValues, viewModel: SettingViewModel) {
    val ipAddress = viewModel.ipAddress.collectAsState()
    val portAddress = viewModel.portAddress.collectAsState()
    val nameDatabase = viewModel.nameDatabaseSettings.collectAsState()
    val loginDatabase = viewModel.loginDatabaseSettings.collectAsState()
    val passwordDatabase = viewModel.passwordDatabaseSettings.collectAsState()
    val serverKeyDatabase = viewModel.serverKeyDatabaseSettings.collectAsState()


    val ipAddressField = remember { mutableStateOf(ipAddress.value) }
    val portAddressField = remember { mutableStateOf(portAddress.value) }
    val nameDatabaseField = remember { mutableStateOf(nameDatabase.value) }
    val loginDatabaseField = remember { mutableStateOf(loginDatabase.value) }
    val passwordDatabaseField = remember { mutableStateOf(passwordDatabase.value) }
    val serverKeyDatabaseField = remember { mutableStateOf(serverKeyDatabase.value) }

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
            Text("Server config", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            HorizontalDivider()
            Spacer(Modifier.height(10.dp))

            InputFieldWithError(
                label = "Server ip",
                value = ipAddressField.value,
                isError = isErrorIp.value,
                onValueChange = {
                    isErrorIp.value = !ipRegex.matches(it) && it.isNotEmpty()
                    viewModel.isError(isErrorIp.value, isErrorPort.value)
                    ipAddressField.value = it
                    viewModel.ipAddressField = it
                },
                errorMessage = "Invalid ip",
                keyboardType = KeyboardType.Number,
                regex = ipRegex,
            )

            InputFieldWithError(
                label = "Port",
                value = portAddressField.value,
                isError = isErrorPort.value,
                onValueChange = {
                    isErrorPort.value = !portRegex.matches(it) && it.isNotEmpty()
                    viewModel.isError(isErrorIp.value, isErrorPort.value)
                    portAddressField.value = it
                    viewModel.portAddressField = it
                },
                errorMessage = "Invalid port",
                keyboardType = KeyboardType.Number,
                regex = portRegex
            )

            Text("Database config", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            HorizontalDivider()
            Spacer(Modifier.height(10.dp))

            InputFieldWithError(
                label = "Name DB",
                value = nameDatabaseField.value,
                isError = false,
                onValueChange = {
                    //isErrorPort.value = !portRegex.matches(it) && it.isNotEmpty()
                    //viewModel.isError(isErrorIp.value, isErrorPort.value)
                    nameDatabaseField.value = it
                    viewModel.nameDatabaseField = it
                },
                errorMessage = "Wrong name",
                keyboardType = KeyboardType.Text,
                regex = portRegex
            )


            InputFieldWithError(
                label = "Login database",
                value = loginDatabaseField.value,
                isError = false,//isErrorPort.value,
                onValueChange = {
                    //isErrorPort.value = !portRegex.matches(it) && it.isNotEmpty()
                    //viewModel.isError(isErrorIp.value, isErrorPort.value)
                    loginDatabaseField.value = it
                    viewModel.loginDatabaseField = it
                },
                errorMessage = "Invalid port",
                keyboardType = KeyboardType.Text,
                regex = portRegex
            )


            InputFieldWithError(
                label = "Password database",
                value = passwordDatabaseField.value,
                isError = false,//isErrorPort.value,
                onValueChange = {
                    //isErrorPort.value = !portRegex.matches(it) && it.isNotEmpty()
                    //viewModel.isError(isErrorIp.value, isErrorPort.value)
                    passwordDatabaseField.value = it
                    viewModel.passwordDatabaseField = it
                },
                errorMessage = "Invalid port",
                keyboardType = KeyboardType.Text,
                regex = portRegex
            )

            InputFieldWithError(
                label = "Server key",
                value = serverKeyDatabaseField.value,
                isError = false,//isErrorPort.value,
                onValueChange = {
                    serverKeyDatabaseField.value = it
                    viewModel.serverKeyField = it
                },
                errorMessage = "Invalid port",
                keyboardType = KeyboardType.Text,
                regex = portRegex
            )

            val isTested = remember { mutableStateOf(false) }

            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!isTested.value) {
                        ButtonDefaults.buttonColors().containerColor
                    } else {
                        Color.Green
                    }
                ),
                onClick = {
                    isTested.value = true
                }) {
                if (!isTested.value) {
                    Text("Test connection")
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Done,
                            tint = Color.White,
                            contentDescription = ""
                        )
                        Spacer(Modifier.width(10.dp))
                        Text("Test connection")
                    }
                }

            }
        }
    }
}