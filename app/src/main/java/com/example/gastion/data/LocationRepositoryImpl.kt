package com.example.gastion.data

import android.content.Context
import android.location.Location
import android.os.Looper
import androidx.annotation.RequiresPermission
import androidx.core.location.LocationCompat
import com.example.gastion.data.LocationRepository.LocationListener
import com.example.gastion.data.di.WebSocketService
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
  @ApplicationContext private val appContext: Context,
) : LocationRepository {

  private val locationRequest =
    LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, UPDATE_INTERVAL).apply {
      setWaitForAccurateLocation(true)
    }.build()

  @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
  override fun getCurrentLocation(
    callBack: LocationListener
  ): Task<Location> {
    val fusedLocationClient: FusedLocationProviderClient =
      LocationServices.getFusedLocationProviderClient(appContext)

    fusedLocationClient.requestLocationUpdates(
      locationRequest,
      object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
          callBack.onLocationResult(locationResult.lastLocation)
        }
      },
      Looper.getMainLooper()
    )

    return fusedLocationClient.getCurrentLocation(
      locationRequest.priority,
      CancellationTokenSource().token
    ).continueWith { task ->
        val location = task.result
        if (LocationCompat.isMock(location)) throw Exception("Location is Mocked") else location
      }
  }

  private fun checkGpsSettings() {
    val request = LocationSettingsRequest.Builder()
      .addLocationRequest(locationRequest)
      .build()

    LocationServices.getSettingsClient(appContext)
      .checkLocationSettings(request)
      .addOnSuccessListener {
        //callback.onCanRequestLocation()
      }
      .addOnFailureListener {
        if (it is ResolvableApiException) {
          // callback.onResolvableApiException(it)
        } else {
          //callback.onOtherErrorResponse(it)
        }
      }
  }

  companion object {
    const val UPDATE_INTERVAL = 1000L
    const val FASTEST_INTERVAL = 1000L
  }
}