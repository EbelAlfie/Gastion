package com.example.gastion.ui.main

import android.location.Location
import androidx.annotation.RequiresPermission
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gastion.data.GasRepository
import com.example.gastion.data.LocationRepository
import com.example.gastion.data.MemberRepository
import com.example.gastion.data.model.UserRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.osmdroid.bonuspack.location.POI
import javax.inject.Inject

/**
 * This will be the only viewModel for all screen
 */
@HiltViewModel
class MainViewModel @Inject constructor(
  private val locationRepository: LocationRepository,
  private val gasRepository: GasRepository,
  private val memberRepository: MemberRepository
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
      gasLocations.value =
        gasRepository.getNearestGasStation(location, 0.03)
    }
  }

//  fun publishLatestLocation(location: Location) {
//    locationRepository.establishConnection()
//  }

  suspend fun login(
    userName: String,
    password: String
  ) {
    val request = UserRequest(
      userName = userName,
      password = password
    )
    viewModelScope.launch {
      memberRepository.login(request).collectLatest {

      }
    }
  }
}