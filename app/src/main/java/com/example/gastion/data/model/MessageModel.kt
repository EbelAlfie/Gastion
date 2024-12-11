package com.example.gastion.data.model

import com.google.gson.annotations.SerializedName

data class MessageModel(
  val device: String
)

data class LocationMessageModel (
  @SerializedName("lat") val latitude: String,
  @SerializedName("lon") val longitude: String,
  @SerializedName("a") val angle: String,
  @SerializedName("h") val h: String,
  @SerializedName("sN") val sN: String
)