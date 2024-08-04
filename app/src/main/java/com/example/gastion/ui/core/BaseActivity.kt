package com.example.gastion.ui.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.gastion.ui.theme.components.AlertBottomSheet
import com.example.gastion.ui.theme.components.Loading

abstract class BaseActivity: ComponentActivity() {

  abstract val uiDelegate: BaseUiDelegate

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MainScreen()

      LoadingContent()

      AlertSheet()
    }
  }

  @Composable
  abstract fun MainScreen()

  @Composable
  open fun LoadingContent() {
    Loading(isDismissible = true)
  }

  @Composable
  open fun AlertSheet() {
    AlertBottomSheet()
  }

}