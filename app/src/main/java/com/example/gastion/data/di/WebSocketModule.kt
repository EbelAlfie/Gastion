package com.example.gastion.data.di

import com.example.gastion.data.util.buildRequest
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okhttp3.logging.HttpLoggingInterceptor
import java.io.UnsupportedEncodingException
import java.util.concurrent.TimeUnit

@Provides
fun httpLogInterceptor(): HttpLoggingInterceptor {
  return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}

@Provides
fun okHttpClient(
  logInterceptor: HttpLoggingInterceptor
): OkHttpClient {
  return OkHttpClient.Builder()
    .addInterceptor(logInterceptor)
    .connectTimeout(15, TimeUnit.SECONDS)
    .readTimeout(15, TimeUnit.SECONDS)
    .build()
}

class WebSocketService {

  fun listenMessage() {

  }
}