package com.example.gastion.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun Loading(
  isDismissible: Boolean,
  onDismiss: () -> Unit = {}
) {
  Dialog(
    properties = DialogProperties(
      dismissOnBackPress = isDismissible,
      dismissOnClickOutside = isDismissible
    ),
    onDismissRequest = onDismiss
  ) {
    Box(
      modifier = Modifier.padding(20.dp).background(Color.White, RoundedCornerShape(5.dp))
    ) {
      CircularProgressIndicator()
    }

  }
}