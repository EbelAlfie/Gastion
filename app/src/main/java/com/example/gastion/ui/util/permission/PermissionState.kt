package com.example.gastion.ui.util.permission

import android.content.Context
import android.content.pm.PackageManager

typealias Permission = String
class PermissionState {
  var permissions: Array<Permission> = arrayOf()
    private set


  fun filterPermission(context: Context) {
    permissions = permissions.filter {
      context.checkSelfPermission(it) == PackageManager.PERMISSION_DENIED
    }.toTypedArray()
  }

  fun requestPermission(
    requiredPermissions: Array<Permission>
  ) {
    permissions = requiredPermissions
  }

}
