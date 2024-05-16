package com.example.gastion.data.model

import android.os.Message

data class MessageModel(
  val device: String
)

data class LocationMqttModel (
  val deviceName: String,
  val topic: String,
  val message: String,
  val lat: String,
  val lon: String,
)