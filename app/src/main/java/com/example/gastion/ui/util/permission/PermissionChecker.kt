package com.example.gastion.ui.util.permission

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun PermissionChecker(
  permissionState: PermissionState
) {
  val context = LocalContext.current
  permissionState.filterPermission(context)

  var permissionLauncher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>? =
    rememberLauncherForActivityResult(
      contract = ActivityResultContracts.RequestMultiplePermissions()
    ) {

    }

  DisposableEffect(permissionState) {
    permissionLauncher?.launch(permissionState.permissions)
    onDispose {
      permissionLauncher = null
    }
  }

}