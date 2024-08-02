package com.example.gastion.data

import com.example.gastion.data.di.WebSocketService
import com.example.gastion.data.model.LocationMessageModel
import javax.inject.Inject

class LiveTrackRepositoryImpl @Inject constructor(
  private val webSocketService: WebSocketService
): LiveTrackRepository {
  override fun establishConnection() {
    webSocketService.establishConnection()
  }

  fun sendLocation(location: LocationMessageModel) {
    webSocketService.publishMesssage(location)
  }
}