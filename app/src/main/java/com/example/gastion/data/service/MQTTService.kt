package com.example.gastion.data.service

import android.content.Context
import android.util.Log
import com.example.gastion.BuildConfig
import com.example.gastion.data.model.LocationMessageModel
import com.google.gson.Gson
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.IMqttActionListener
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.IMqttToken
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage
import javax.inject.Inject
import javax.inject.Named

/**
 * Mqtt service provider
 */
@Named("false")
class MQTTService @Inject constructor(
  context: Context,
  private var clientId: String
): LiveTrackService {
  var isConnected: Boolean = false

  private var mqttClient: MqttAndroidClient? = null

  init {
    val serverURI = ""
    mqttClient = MqttAndroidClient(context, serverURI, clientId)
  }

  fun connect(callback: (Boolean) -> Unit) {
    mqttClient?.setCallback(object : MqttCallback {
      override fun messageArrived(topic: String?, message: MqttMessage?) {
        Log.i("Info", "Receive message: ${message.toString()} from topic: $topic")
      }

      override fun connectionLost(cause: Throwable?) {
        Log.i("Info", "Connection lost ${cause.toString()}")
      }

      override fun deliveryComplete(token: IMqttDeliveryToken?) {

      }
    })
    val options = MqttConnectOptions()
    options.userName = "admin"
    options.password = "12345678".toCharArray()
    try {
      mqttClient?.connect(options, null, object : IMqttActionListener {
        override fun onSuccess(asyncActionToken: IMqttToken?) {
          Log.i("Info", "Connection success")
          subscribe()
          isConnected = true
          callback(true)
        }

        override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
          Log.i("Info", "Connection failure")
          callback(false)
        }
      })
    } catch (e: MqttException) {
      e.printStackTrace()
    }
  }

  override fun subscribe() {
    try {
      //must be same size topics and qos
      val topics = arrayOf("myTopic", this.clientId)
      val qos = intArrayOf(1, 1)
      mqttClient?.subscribe(topics, qos, null, object : IMqttActionListener {
        override fun onSuccess(asyncActionToken: IMqttToken?) {
          Log.i("Info", "Subscribed to multiple topics successfully")
        }

        override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
          Log.i("Info", "Failed to subscribe to multiple topics $exception")
        }
      })
    } catch (ex: MqttException) {
      ex.printStackTrace()
    } catch (ex: IllegalArgumentException) {
      ex.printStackTrace()
    }
  }

  override fun publish(locationMessageModel: LocationMessageModel) {
    val gson = Gson()
    val message = gson.toJson(locationMessageModel, LocationMessageModel::class.java)
    val qos = 1
    val retained = false
    Log.i("Info", "message sent ${message}")
    mqttClient?.publish("topic", message.toByteArray(), qos, retained)
  }

  fun publish(
    deviceName: String,
    topic: String,
    message: String,
    lat: String,
    lon: String
  ) {
    val gson = Gson()
    val message = gson.toJson(LocationMessageModel(deviceName, topic, message, lat, lon))
    val qos = 1
    val retained = false
    Log.i("Info", "message sent ${message}")
    mqttClient?.publish(topic, message.toByteArray(), qos, retained)
  }

}