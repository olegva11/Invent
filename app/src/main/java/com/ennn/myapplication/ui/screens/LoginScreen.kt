package com.ennn.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ennn.myapplication.R
import com.ennn.myapplication.navigation.ScreenRoute
import com.ennn.myapplication.ui.SharedViewModel

@Composable
fun LoginScreen(innerPadding: PaddingValues, viewModel: SharedViewModel, navController: NavHostController) {
    var password by remember { mutableStateOf("") }

    Box( modifier = Modifier
        .padding(innerPadding).fillMaxSize()){
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DropDownUsersList(viewModel)
            Spacer(modifier = Modifier.height(16.dp))
            PasswordTextField(
                password = password,
                onPasswordChange = { newPassword -> password = newPassword }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                navController.navigate(ScreenRoute.InventMenuScreen.route)
            }) {
                Text(stringResource(R.string.login_button))
            }
        }
    }

}

@Composable
fun DropDownUsersList(viewModel: SharedViewModel)
{
    val options = listOf("Користувач 1", "Користувач 2", "Користувач 3")
    var userName by remember { mutableStateOf("") }

    var selectedOption by remember { mutableStateOf(options[0]) }

    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth().border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
    ) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = true }, shape = RoundedCornerShape(1.dp)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp), horizontalArrangement = Arrangement.SpaceBetween){
                Text(text = userName.ifEmpty { stringResource(R.string.select_user) })
                Image(imageVector = Icons.Default.ArrowDropDown,"")
            }
        }

        DropdownMenu(
            modifier = Modifier,
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(text = { Text(option) }, onClick = {
                    userName = option
                    selectedOption = option
                    expanded = false
                })
            }
        }
    }
}

@Composable
fun PasswordTextField(password: String, onPasswordChange: (String) -> Unit) {
    var passwordVisible by remember { mutableStateOf(false) }

    TextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text(stringResource(R.string.input_pass)) },
        singleLine = true,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisible) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, contentDescription = if (passwordVisible) "Приховати пароль" else "Показати пароль")
            }
        },
        modifier = Modifier.fillMaxWidth().border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
    )
}