package com.example.gastion.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.Modifier
import com.example.gastion.ui.core.BaseActivity
import com.example.gastion.ui.core.BaseUiDelegate
import com.example.gastion.ui.screens.gasmap.GasMapScreen
import com.example.gastion.ui.theme.GastionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<>() {

  override val uiDelegate: BaseUiDelegate<>

  private val viewModel: MainViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      GastionTheme {
        GasMapScreen(
          modifier = Modifier,
          brain = viewModel
        )
      }
    }
  }
}