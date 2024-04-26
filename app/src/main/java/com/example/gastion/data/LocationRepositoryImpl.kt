package com.example.gastion.data

import android.content.Context
import android.location.Location
import androidx.annotation.RequiresPermission
import androidx.core.location.LocationCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task

class LocationRepositoryImpl : LocationRepository {

  private val locationRequest = LocationRequest.Builder(Priority.PRIORITY_BALANCED_POWER_ACCURACY, 10000).apply {
    setWaitForAccurateLocation(true)
  }.build()

  @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
  override fun getCurrentLocation(appContext: Context): Task<Location> {
    val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(appContext)

    return fusedLocationClient.getCurrentLocation(locationRequest.priority, CancellationTokenSource().token)
      .continueWith { task ->
        val location = task.result
        if (LocationCompat.isMock(location)) throw Exception("Location is Mocked") else location
      }
  }


}