package com.example.gastion.ui.main

import com.example.gastion.ui.core.BaseScreenModel

sealed interface MainScreens: BaseScreenModel {

  data object Login: MainScreens

  data object Maps: MainScreens
}