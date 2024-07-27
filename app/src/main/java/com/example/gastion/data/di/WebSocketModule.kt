package com.example.gastion.data.di

import android.util.Log
import com.example.gastion.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import javax.inject.Inject

class WebSocketService @Inject constructor(
  private val httpClient: OkHttpClient
) {

  fun listenMessage() {
    httpClient.newWebSocket(buildRequest(), object : WebSocketListener() {
      override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        Log.d("WSCON", "onOpen: ")
      }

      override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        super.onMessage(webSocket, bytes)
        Log.d("WSCON", "onMessage: ${bytes}")
      }

      override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
        Log.d("WSCON", "onFailed: ${t.message}")
      }
    })
  }

  private fun buildRequest(): Request =
    Request.Builder()
      .url(BuildConfig.wss_url)
      .build()
}