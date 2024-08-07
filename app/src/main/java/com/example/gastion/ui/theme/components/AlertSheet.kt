package com.example.gastion.ui.theme.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable

data class SheetData(
  @DrawableRes val image: Int,
  @StringRes val title: Int,
  @StringRes val message: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertBottomSheet(
  data: SheetData?,
  onDismiss: () -> Unit
) {
  val sheetState = rememberModalBottomSheetState(false)

  ModalBottomSheet(onDismissRequest = onDismiss) {

  }
}