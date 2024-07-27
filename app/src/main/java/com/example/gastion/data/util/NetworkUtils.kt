package com.example.gastion.data.util

import com.example.gastion.BuildConfig
import okhttp3.Request

fun buildRequest(): Request =
  Request.Builder()
    .url(BuildConfig.wss_url)
    .build()