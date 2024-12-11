package com.example.gastion.ui.theme.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInput(
  value: String,
  hint: String,
  isEnabled: Boolean = true,
  isError: Boolean = false,
  onValueChange: (String) -> Unit
) {
  OutlinedTextField(
    value = value,
    placeholder = { Text(hint) },
    onValueChange = onValueChange,
    shape = OutlinedTextFieldDefaults.shape,
    colors = TextFieldDefaults.colors(),
    enabled = isEnabled,
    isError = isError,
  )
}