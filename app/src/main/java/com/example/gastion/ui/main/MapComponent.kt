package com.example.gastion.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun GasMap(
  modifier: Modifier = Modifier,
  brain: MainViewModel
) {
  val userLocation by brain.userLocation.collectAsState()
  val myLoc by remember(userLocation) {
    mutableStateOf(LatLng(userLocation?.latitude ?: 0.0, userLocation?.longitude ?: 0.0))
  }
  val cameraPositionState = rememberCameraPositionState {}
  cameraPositionState.position = CameraPosition.fromLatLngZoom(myLoc, 20f)

  LaunchedEffect(Unit) {
    brain.requestLocationUpdate()
  }

  GoogleMap(
    modifier = modifier,
    cameraPositionState = cameraPositionState,
  ) {
    Marker(
      state = MarkerState(position = myLoc),
      title = "Me",
      snippet = "Me Me Me Me"
    )
  }
}