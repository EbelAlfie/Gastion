package com.example.gastion.ui.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class BottomSheetState <contentData> {
  var isBottomSheetVisible by mutableStateOf(false)
    private set

  var data: contentData? = null
    private set

  var isDismisable = true
    private set

  var onDismissRequest: (() -> Unit)? = null
    private set

  fun showBottomSheet(
    newData: contentData,
    isDismisable: Boolean = true,
    onDismissRequest: (() -> Unit)? = null
  ) {
    data = newData
    this.isDismisable = isDismisable
    this.onDismissRequest = onDismissRequest
    isBottomSheetVisible = true
  }

  fun dismissBottomSheet() {
    onDismissRequest?.invoke()
    isBottomSheetVisible = false
  }
}

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