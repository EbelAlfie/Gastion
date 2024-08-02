package com.example.gastion.data.service

import com.example.gastion.data.model.LocationMessageModel

interface LiveTrackService {

  fun subscribe()

  fun publish(locationMessageModel: LocationMessageModel)
}