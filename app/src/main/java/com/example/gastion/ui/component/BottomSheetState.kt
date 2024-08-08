package com.example.gastion.ui.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class BottomSheetData(
  @DrawableRes val image: Int,
  @StringRes val title: Int,
  @StringRes val content: Int,
  @StringRes val positiveLabel: Int? = null,
  @StringRes val negativeLabel: Int? = null,
  val onDismissRequest: (() -> Unit)? = null,
  val onPositiveClicked: (() -> Unit)? = null,
  val onNegativeClicked: (() -> Unit)? = null,
)