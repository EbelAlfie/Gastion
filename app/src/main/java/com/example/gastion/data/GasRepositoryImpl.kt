package com.example.gastion.data

import android.location.Location
import org.osmdroid.bonuspack.location.NominatimPOIProvider
import org.osmdroid.bonuspack.location.POI
import org.osmdroid.bonuspack.location.POI.POI_SERVICE_NOMINATIM
import org.osmdroid.util.GeoPoint
import javax.inject.Inject


class GasRepositoryImpl @Inject constructor(): GasRepository {

  override fun getNearestGasStation(myPosition: Location, maxDistance: Double): ArrayList<POI> {
    val geoPoint = GeoPoint(myPosition.latitude, myPosition.longitude)

    val poiProvider = NominatimPOIProvider("OSMBonusPackTutoUserAgent")
    return try {
      poiProvider.getPOICloseTo(geoPoint, "fuel", 100, maxDistance)
    } catch (e: Throwable) {
      arrayListOf(POI(POI_SERVICE_NOMINATIM))
    }
  }

}