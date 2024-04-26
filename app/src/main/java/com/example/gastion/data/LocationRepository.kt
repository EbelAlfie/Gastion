package com.example.gastion.data

import android.content.Context
import android.location.Location
import com.google.android.gms.tasks.Task

interface LocationRepository {
  fun getCurrentLocation(appContext: Context): Task<Location>
}