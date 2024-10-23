package com.ennn.myapplication.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputFieldWithError(
    label: String,
    value: String,
    isError: Boolean,
    onValueChange: (String) -> Unit,
    errorMessage: String,
    keyboardType: KeyboardType,
    regex: Regex,
) {
    val focusManager = LocalFocusManager.current

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp), text = label
    )

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp, color = if (isError) {
                    Color.Red
                } else {
                    Color.White
                }, shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp)),
        maxLines = 1,
        singleLine = true,
        value = value,
        isError = isError,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next,
            keyboardType = keyboardType
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                if (!focusManager.moveFocus(FocusDirection.Down))
                    focusManager.clearFocus()
            },
            onDone = {
                if (!focusManager.moveFocus(FocusDirection.Down))
                    focusManager.clearFocus()
            }
        ),
        onValueChange = {
            val hasError = !regex.matches(it) && it.isNotEmpty()
            onValueChange(it)
        },
    )

    Text(
        text = if (isError) {
            errorMessage
        } else {
            " "
        },
        fontSize = 15.sp,
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(start = 16.dp)
    )
}
