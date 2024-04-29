package com.example.gastion.data

import android.location.Location
import android.util.Log
import org.osmdroid.bonuspack.location.NominatimPOIProvider
import org.osmdroid.bonuspack.location.POI
import org.osmdroid.util.GeoPoint


class GasSource {

  fun getNearestGasStation(myPosition: GeoPoint, maxDistance: Double) {
    val poiProvider: NominatimPOIProvider = NominatimPOIProvider("OSMBonusPackTutoUserAgent")
    val nearestGases: ArrayList<POI> = poiProvider.getPOICloseTo(myPosition, "fuel", 10, maxDistance)
    Log.d("GASES", "$nearestGases")
  }

}