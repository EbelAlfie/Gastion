package com.example.gastion

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GasApp: Application() {

  override fun onCreate() {
    super.onCreate()
  }
}
