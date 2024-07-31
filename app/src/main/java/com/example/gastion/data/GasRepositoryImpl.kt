package com.example.gastion.data

import android.location.Location
import org.osmdroid.bonuspack.location.NominatimPOIProvider
import org.osmdroid.bonuspack.location.POI
import org.osmdroid.util.GeoPoint
import javax.inject.Inject


class GasRepositoryImpl @Inject constructor(): GasRepository {

  override fun getNearestGasStation(myPosition: Location, maxDistance: Double): ArrayList<POI> {
    val geoPoint = GeoPoint(myPosition.latitude, myPosition.longitude)

    val poiProvider = NominatimPOIProvider("OSMBonusPackTutoUserAgent")
    return poiProvider.getPOICloseTo(geoPoint, "fuel", 100, maxDistance)
  }

}