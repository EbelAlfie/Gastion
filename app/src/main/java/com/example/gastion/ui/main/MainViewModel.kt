package com.example.gastion.ui.main

import android.location.Location
import androidx.annotation.RequiresPermission
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gastion.data.GasRepository
import com.example.gastion.data.LocationRepository
import com.example.gastion.data.MemberRepository
import com.example.gastion.data.model.UserRequest
import com.example.gastion.ui.core.BaseUiEvents
import com.example.gastion.ui.core.BaseUiState
import com.example.gastion.ui.core.BaseViewModel
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
) : BaseViewModel<MainScreens>() {

  override fun getInitialUiState(): MainScreens  = MainScreens.Login()

  @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
  fun requestLocationUpdate() {
    locationRepository.getCurrentLocation(
      object : LocationRepository.LocationListener {
        override fun onLocationResult(location: Location?) {
          setState {
            (this as? MainScreens.Maps)?.let {
              //search nearest gas station every x meter
              if (location == null) return@let null

              operateLocation(location)

              copy (myLocation = location)
            }
          }
        }
      }
    )

  }

  private fun operateLocation(location: Location) {
    val pastLocation = (uiState.value as? MainScreens.Maps)?.myLocation ?: return
    if (pastLocation.distanceTo(location) >= 3.0) {
      searchNearestGasStation(location)
      //publishLatestLocation(location)
    }
  }

  private fun searchNearestGasStation(location: Location) {
    viewModelScope.launch(Dispatchers.IO) {
      val gasLocations =
        gasRepository.getNearestGasStation(location, 0.03)
      setState { (this as? MainScreens.Maps)?.copy(gasLocations = gasLocations) }
    }
  }

//  fun publishLatestLocation(location: Location) {
//    locationRepository.establishConnection()
//  }

  /** Login event handler functions **/
  fun login(uiState: MainScreens.Login) {
    val request = UserRequest(
      userName = uiState.userName,
      password = uiState.password
    )
    viewModelScope.launch {
      memberRepository.login(request).collectLatest {

      }
    }
  }
  fun onNameChanged(newName: String) {
    setState { (this as? MainScreens.Login)?.copy(userName = newName) }
  }
  fun onPassChanged(newPass: String) {
    setState { (this as? MainScreens.Login)?.copy(password = newPass) }
  }
}