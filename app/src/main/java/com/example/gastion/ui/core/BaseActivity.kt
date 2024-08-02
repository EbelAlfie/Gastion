package com.example.gastion.ui.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

abstract class BaseActivity: ComponentActivity() {

  abstract val uiDelegate: BaseUiDelegate

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {

    }
  }
}