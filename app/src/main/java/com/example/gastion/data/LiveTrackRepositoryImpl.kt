package com.example.gastion.data

import com.example.gastion.data.service.WebSocketService
import com.example.gastion.data.model.LocationMessageModel
import com.example.gastion.data.service.MemberService
import com.google.gson.Gson
import javax.inject.Inject

/**
 * This repository class is responsible for websocket related connection, such as
 * publishing and listening to a message.
 */
class LiveTrackRepositoryImpl @Inject constructor(
  private val webSocketService: WebSocketService,
  private val memberService: MemberService
): LiveTrackRepository {
  override fun establishConnection() {
    webSocketService.establishConnection()
  }

  fun sendLocation(location: LocationMessageModel) {
    val message = Gson().toJson(location, LocationMessageModel::class.java)
    webSocketService.publishMesssage(message)
  }
}