package com.example.gastion.ui.core

import androidx.annotation.StringRes
import com.example.gastion.ui.theme.components.SheetData

data class MessageState(
  @StringRes val toastData: Int? = null,
  val isLoading: Boolean = false,
  val sheetData: SheetData? = null
)
