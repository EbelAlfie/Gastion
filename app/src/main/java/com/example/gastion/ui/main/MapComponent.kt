package com.example.gastion.ui.main

import android.Manifest.permission
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.example.gastion.ui.util.permission.PermissionChecker
import com.example.gastion.ui.util.permission.PermissionState
import com.example.gastion.ui.util.permission.PermissionHelper
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun GasMap(
  modifier: Modifier = Modifier,
  brain: MainViewModel
) {
  val context = LocalContext.current
  val permissionState = remember { PermissionState() }
  val userLocation by brain.userLocation.collectAsState()
  val myLoc by remember(userLocation) {
    mutableStateOf(LatLng(userLocation?.latitude ?: 0.0, userLocation?.longitude ?: 0.0))
  }
  val cameraPositionState = rememberCameraPositionState {
    position = CameraPosition.fromLatLngZoom(myLoc, 20f)
  }

  var properties by remember {
    mutableStateOf(MapProperties(isMyLocationEnabled = true))
  }

  LaunchedEffect(Unit) {
    permissionState.requestPermission(
      requiredPermissions = PermissionHelper.locationPermissions,
    ) {
      if (ActivityCompat.checkSelfPermission(
          context,
          permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
          context,
          permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
      ) {
        return@requestPermission
      }
      brain.requestLocationUpdate()
    }
  }

  PermissionChecker(permissionState)

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
      brain.gasLocations.collectAsState().value.forEach {
        Log.d("GASS", it.mDescription)
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
  }

}