package com.example.gastion.ui.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable

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

  }

  @Composable
  open fun AlertSheet() {

  }

}