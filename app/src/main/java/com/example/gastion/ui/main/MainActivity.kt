package com.example.gastion.ui.main

import androidx.activity.compose.BackHandler
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.gastion.ui.core.BaseActivity
import com.example.gastion.ui.core.Screen
import com.example.gastion.ui.main.MainScreens.Login
import com.example.gastion.ui.main.MainScreens.Maps
import com.example.gastion.ui.screens.gasmap.GasMapScreen
import com.example.gastion.ui.screens.login.LoginScreen
import com.example.gastion.ui.theme.GastionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainScreens>() {

  override val viewModel: MainViewModel by viewModels()

  @Composable
  override fun MainScreen() {
    GastionTheme {
      val screen by viewModel.uiState.collectAsState()

      Screen<Login>(screen) {
        LoginScreen(
          uiState = it,
          onLogin = viewModel::login,
          onNameChange = viewModel::onNameChanged,
          onPassChange = viewModel::onPassChanged,
        )
      }

      Screen<Maps>(screen) {
        GasMapScreen(
          uiState = it,
          requestLocationUpdate = viewModel::requestLocationUpdate
        )
      }
    }
  }

  override fun eventHandler(events: Any) {}
}