package com.example.gastion.ui.main

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.gastion.R
import com.example.gastion.ui.component.BottomSheetContainer
import com.example.gastion.ui.component.BottomSheetData
import com.example.gastion.ui.component.BottomSheetErrorContent
import com.example.gastion.ui.component.BottomSheetState
import com.example.gastion.ui.util.permission.PermissionChecker
import com.example.gastion.ui.util.permission.PermissionHelper
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
fun GasMap(
  modifier: Modifier = Modifier,
  brain: MainViewModel
) {
  val context = LocalContext.current
  val permissionState = remember { PermissionState() }
  val bottomSheetState = remember { BottomSheetState<BottomSheetData>() }

  val userLocation by brain.userLocation.collectAsState()
  val myLoc by remember(userLocation) {
    mutableStateOf(LatLng(userLocation?.latitude ?: -6.197302, userLocation?.longitude ?: 106.819142))
  }
  val cameraPositionState = rememberCameraPositionState {
    position = CameraPosition.fromLatLngZoom(myLoc, 20f)
  }

  val properties by remember {
    mutableStateOf(MapProperties(isMyLocationEnabled = true))
  }

  PermissionChecker(permissionState)

  BottomSheetContainer(sheetState = bottomSheetState) {
    BottomSheetErrorContent(
      sheetData = it.data ?: return@BottomSheetContainer,
      it.onDismissRequest
    )
  }

  permissionState.requestPermission(
    requiredPermissions = PermissionHelper.locationPermissions,
    onPermissionGranted = brain::requestLocationUpdate,
    onPermissionDenied = {
      bottomSheetState.showBottomSheet(
        BottomSheetData(
          image = R.drawable.ic_gas_station,
          title = R.string.permission_denied_title,
          content = R.string.permission_denied_content
        )
      )
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
      brain.gasLocations.collectAsState().value.forEach {
        Log.d("GASS", it.mDescription)
        val latLong = LatLng(it.mLocation.latitude, it.mLocation.longitude)
        Marker(
          state = MarkerState(position = latLong),
          title = it.mDescription.substringBefore(","),
          snippet = it.mDescription.substringAfter(","),
          onClick = { marker ->
            cameraPositionState.position = CameraPosition.fromLatLngZoom(marker.position, 20f)
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