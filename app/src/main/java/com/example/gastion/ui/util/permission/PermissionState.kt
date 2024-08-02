package com.example.gastion.ui.util.permission

typealias Permission = String

data class PermissionState(
  val permissions: List<Permission> = listOf(),
  val permissionListener: PermissionResult? = null
)

interface PermissionResult {
  fun onAllGranted()
  fun onAnyDenied(permission: Permission)
}
