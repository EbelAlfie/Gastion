package com.example.gastion.data

import android.location.Location
import androidx.annotation.RequiresPermission
import com.google.android.gms.tasks.Task

interface LocationRepository {

  @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
  fun getCurrentLocation(callBack: LocationListener): Task<Location>

  fun checkGpsSettings()

  interface LocationListener {
    fun onLocationResult(location: Location?)
  }
}