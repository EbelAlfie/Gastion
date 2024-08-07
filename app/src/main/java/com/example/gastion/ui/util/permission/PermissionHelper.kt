package com.example.gastion.ui.util.permission

import android.Manifest


object PermissionHelper {

  val locationPermissions
    get() = listOf(
      Manifest.permission.ACCESS_COARSE_LOCATION,
      Manifest.permission.ACCESS_FINE_LOCATION
    )
}