package com.example.gastion.ui.main

import android.location.Location
import androidx.annotation.RequiresPermission
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gastion.data.GasSource
import com.example.gastion.data.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.osmdroid.bonuspack.location.POI
import org.osmdroid.util.GeoPoint
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  private val locationRepository: LocationRepository,
  private val gasSource: GasSource,
) : ViewModel() {
//  private val currentInfo: LocationController.SharingLocationInfo? = null
//  private val liveLocation: LocationActivity.LiveLocation? = null
  private val location = Location("network")
  val userLocation = MutableStateFlow<Location?>(null)
  val nearestGasStations = MutableStateFlow<ArrayList<Location>>(arrayListOf())

  val gasLocations = MutableStateFlow<ArrayList<POI>>(arrayListOf())

  @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
  fun requestLocationUpdate() {
    locationRepository.getCurrentLocation(
      object : LocationRepository.LocationListener {
        override fun onLocationResult(location: Location?) {
          //search nearest gas station every x meter
          if (location == null) return

          operateLocation(location)

          userLocation.value = location
        }
      }
    )

  }

  private fun operateLocation(location: Location) {
    val pastLocation = userLocation.value
    if (pastLocation == null || pastLocation.distanceTo(location) >= 3.0) {
      searchNearestGasStation(location)
      publishLatestLocation(location)
    }
  }

  private fun searchNearestGasStation(location: Location) {
    viewModelScope.launch(Dispatchers.IO) {
      gasLocations.value = gasSource.getNearestGasStation(GeoPoint(location.latitude, location.longitude), 0.03)
    }
  }

  fun publishLatestLocation(location: Location) {

  }
}