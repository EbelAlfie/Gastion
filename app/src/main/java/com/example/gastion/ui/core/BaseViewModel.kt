package com.example.gastion.ui.core

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel <screen: BaseScreenModel, event: BaseUiEvents>: ViewModel() {
  val messageState = MutableStateFlow(MessageState())

  abstract var uiEvents: MutableState<event>

  private fun publishEvent(event: event) {
    uiEvents.value = event
  }
}