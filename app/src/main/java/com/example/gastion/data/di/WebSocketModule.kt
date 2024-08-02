package com.example.gastion.data.di

import android.util.Log
import com.example.gastion.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

  private var webSocket : WebSocket? = null

  fun establishConnection(
    url: String = "${BuildConfig.wss_url}send/"
  ) {
    webSocket = httpClient.newWebSocket(buildRequest(url), object : WebSocketListener() {
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

  fun publishMesssage(message: String) {
    webSocket?.send(message)
  }

  private fun buildRequest(url: String): Request =
    Request.Builder()
      .url(url)
      .build()
}