package com.example.gastion

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel
import io.getstream.chat.android.offline.plugin.factory.StreamOfflinePluginFactory
import io.getstream.chat.android.state.plugin.config.StatePluginConfig
import io.getstream.chat.android.state.plugin.factory.StreamStatePluginFactory
import io.getstream.video.android.model.ApiKey

@HiltAndroidApp
class GasApp: Application() {

  override fun onCreate() {
    super.onCreate()
    initializeStreamChatSdk()
  }

  private fun initializeStreamChatSdk() {
    val statePluginFactory = StreamStatePluginFactory(
      config = StatePluginConfig(
        backgroundSyncEnabled = true,
        userPresence = true,
      ),
      appContext = this,
    )

    val offlinePluginFactory = StreamOfflinePluginFactory(appContext = this)

    ChatClient.Builder(ApiKey(), this)
      .withPlugins(offlinePluginFactory)
      .withPlugins(statePluginFactory)
      .logLevel(ChatLogLevel.ALL)
      .build()
  }
}
