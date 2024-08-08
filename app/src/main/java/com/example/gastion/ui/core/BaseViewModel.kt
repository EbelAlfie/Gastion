package com.example.gastion.ui.core

import androidx.lifecycle.ViewModel
import com.example.gastion.ui.core.message.MessageState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel <state: BaseUiState>: ViewModel() {

  val messageState = MutableStateFlow(MessageState())


  private val initialState: state by lazy(::getInitialUiState)
  abstract fun getInitialUiState(): state

  private val _uiState: MutableStateFlow<state> = MutableStateFlow(initialState)
  val uiState: StateFlow<state> = _uiState

  protected fun setState(newState: state.() -> state?) {
    _uiState.update { newState.invoke(it) ?: it }
  }

  protected fun setMessageState(newState: (MessageState) -> MessageState) {
    messageState.update(newState)
  }

  fun onToastShown() {
    setMessageState { it.copy(toastData = null) }
  }

  fun dismissBottomSheet() {
    setMessageState { it.copy(sheetData = null) }
  }
}