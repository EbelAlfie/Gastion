package com.example.gastion.ui.core.message

import androidx.annotation.StringRes
import com.example.gastion.ui.component.BottomSheetData

data class MessageState(
  @StringRes val toastData: Int? = null,
  val isLoading: Boolean = false,
  val sheetData: BottomSheetData? = null
)
