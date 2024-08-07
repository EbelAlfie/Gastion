package com.example.gastion.ui.main

import android.location.Location
import com.example.gastion.ui.core.BaseUiState
import org.osmdroid.bonuspack.location.POI

sealed interface MainScreens: BaseUiState {

  data class Login(
    val userName: String = "",
    val password: String = ""
  ): MainScreens

  data class Maps(
    val myLocation: Location? = null,
    val gasLocations: ArrayList<POI> = arrayListOf()
  ): MainScreens
}