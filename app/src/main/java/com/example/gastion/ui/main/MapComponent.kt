package com.example.gastion.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun GasMap(
  modifier: Modifier = Modifier
) {
  val myLoc = LatLng(1.35, 103.87)
  val cameraPositionState = rememberCameraPositionState {
    position = CameraPosition.fromLatLngZoom(myLoc, 10f)
  }
  GoogleMap(
    modifier = modifier,
    cameraPositionState = cameraPositionState
  ) {
    Marker(
      state = MarkerState(position = myLoc),
      title = "Me",
      snippet = "Me Me Me Me"
    )
  }
}