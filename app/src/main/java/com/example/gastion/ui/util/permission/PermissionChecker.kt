package com.example.gastion.ui.util.permission

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import okhttp3.internal.notify

@Composable
fun PermissionChecker(
  permissionState: PermissionState
) {
  with (permissionState) {
    if (permissions.isEmpty()) return

    var permissionLauncher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>? =
      rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { result ->
          val deniedPermissions = result.filter { !it.value }.map { it.key }
          if (deniedPermissions.isEmpty())
            permissionListener?.onAllGranted()
          else
            permissionListener?.onAnyDenied(deniedPermissions.firstOrNull() ?: "")
        }
      )

    DisposableEffect(permissions) {
      permissionLauncher?.launch(permissions.toTypedArray())
      onDispose {
        permissionLauncher = null
      }
    }

  }

}