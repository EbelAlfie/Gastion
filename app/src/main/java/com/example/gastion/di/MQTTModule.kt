package com.example.gastion.di

import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

class MQTTModule {
  private fun initMqttClient() {
    val topic = "MQTT Examples"
    val content = "Message from MqttPublishSample"
    val qos = 2
    val broker = "tcp://mqtt.eclipseprojects.io:1883"
    val clientId = "JavaSample"
    val persistence = MemoryPersistence()

    try {
      val sampleClient = MqttClient(broker, clientId, persistence)
      val connOpts = MqttConnectOptions()
      connOpts.isCleanSession = true
      println("Connecting to broker: $broker")
      sampleClient.connect(connOpts)
      println("Connected")
      println("Publishing message: $content")
      val message = MqttMessage(content.toByteArray())
      message.setQos(qos)
      sampleClient.publish(topic, message)
      println("Message published")
      sampleClient.disconnect()
      println("Disconnected")
      System.exit(0)
    } catch (me: MqttException) {
      println("reason " + me.reasonCode)
      println("msg " + me.message)
      println("loc " + me.localizedMessage)
      println("cause " + me.cause)
      println("excep $me")
      me.printStackTrace()
    }
  }
}