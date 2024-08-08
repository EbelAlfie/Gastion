package com.example.gastion.ui.screens.gasmap

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.gastion.ui.main.MainScreens
import com.example.gastion.ui.util.permission.Permission
import com.example.gastion.ui.util.permission.PermissionChecker
import com.example.gastion.ui.util.permission.PermissionHelper
import com.example.gastion.ui.util.permission.PermissionResult
import com.example.gastion.ui.util.permission.PermissionState
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun GasMapScreen(
  modifier: Modifier = Modifier,
  uiState: MainScreens.Maps,
  requestLocationUpdate: () -> Unit
) {
  var permissionState by remember { mutableStateOf(PermissionState()) }

  val userLocation = uiState.myLocation
  val gasLocations = uiState.gasLocations

  val myLoc by remember(userLocation) {
    mutableStateOf(LatLng(userLocation?.latitude ?: -6.197302, userLocation?.longitude ?: 106.819142))
  }
  val cameraPositionState = rememberCameraPositionState {
    position = CameraPosition.fromLatLngZoom(myLoc, 20f)
  }

  val properties by remember {
    mutableStateOf(MapProperties(isMyLocationEnabled = true))
  }

  PermissionChecker(
    permissionState = permissionState,
  )

  permissionState = PermissionState(
    permissions = PermissionHelper.locationPermissions,
    permissionListener = object: PermissionResult {
      @SuppressLint("MissingPermission")
      override fun onAllGranted() {
        requestLocationUpdate()
      }

      override fun onAnyDenied(permission: Permission) {

      }

    }
  )

  Box {
    GoogleMap(
      modifier = modifier,
      cameraPositionState = cameraPositionState,
      properties = properties,
      onMapLoaded = {
        cameraPositionState.position =
          CameraPosition.fromLatLngZoom(myLoc, 20f)
      }
    ) {
      Marker(
        state = MarkerState(myLoc),
        title = "ME",
        snippet = "MEMEMEMEMMEEEE",
        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
      )
      gasLocations.forEach {
        val latLong = LatLng(it.mLocation.latitude, it.mLocation.longitude)
        Marker(
          state = MarkerState(position = latLong),
          title = it.mDescription.substringBefore(","),
          snippet = it.mDescription.substringAfter(","),
          onClick = { marker ->
            cameraPositionState.position =
              CameraPosition.fromLatLngZoom(marker.position, 20f)
            false
          }
        )
      }
    }
    AccuracyText(
      modifier = Modifier.fillMaxWidth(),
      location = userLocation
    )
  }

}