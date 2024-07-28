package com.example.gastion.data

import android.location.Location
import org.osmdroid.bonuspack.location.POI

interface GasRepository {
  fun getNearestGasStation(myPosition: Location, maxDistance: Double): ArrayList<POI>
}