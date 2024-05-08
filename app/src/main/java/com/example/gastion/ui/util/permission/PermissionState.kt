package com.example.gastion.ui.util.permission

import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

typealias Permission = String

interface PermissionResult {
  fun onAllGranted()
  fun onAnyDenied(permission: Permission)
}

class PermissionState {
  var permissions: Array<Permission> = arrayOf()
    private set

  var listener: PermissionResult? = null
  private set

  val isAllGranted by derivedStateOf { permissions.isEmpty() }

  val isAnyDenied by derivedStateOf { permissions.isNotEmpty() }

  fun filterPermission(context: Context) {
    permissions = permissions.filter {
      context.checkSelfPermission(it) == PackageManager.PERMISSION_DENIED
    }.toTypedArray()
  }

  fun requestPermission(
    requiredPermissions: Array<Permission>,
    onPermissionGranted: () -> Unit
  ) {
    permissions = requiredPermissions
    listener = object: PermissionResult {
      override fun onAllGranted() {
        onPermissionGranted.invoke()
      }

      override fun onAnyDenied(permission: Permission) {

      }

    }
  }

  fun onPermissionResult(result: Map<String, Boolean>) {
    permissions = result.filter { !it.value }.map {it.key}.toTypedArray()
  }

}
