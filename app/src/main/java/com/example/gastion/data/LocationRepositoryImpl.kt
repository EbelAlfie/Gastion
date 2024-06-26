package com.example.gastion.data

import android.content.Context
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.core.location.LocationCompat
import com.example.gastion.data.LocationRepository.LocationListener
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
import org.osmdroid.bonuspack.location.POI
import org.osmdroid.util.GeoPoint
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
  @ApplicationContext private val appContext: Context,
  private val gasSource: GasSource
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
    ).addOnSuccessListener {
      Log.d("LOCCCC", "suc: ")
    }.addOnFailureListener {
      Log.d("LOCCCC", "fail: $it")
    }
      .continueWith { task ->
        val location = task.result
        if (LocationCompat.isMock(location)) throw Exception("Location is Mocked") else location
      }
  }

  override fun checkGpsSettings() {
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

  override fun getNearestGasStation(nearestFrom: Location, maxDistance: Double): ArrayList<POI> {
    val geoPoint = GeoPoint(nearestFrom.latitude, nearestFrom.longitude)
    return gasSource.getNearestGasStation(geoPoint, maxDistance)
  }

  companion object {
    const val UPDATE_INTERVAL = 1000L
    const val FASTEST_INTERVAL = 1000L
  }
}