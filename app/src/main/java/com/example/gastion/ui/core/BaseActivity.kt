package com.example.gastion.ui.core

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import com.example.gastion.ui.theme.components.AlertBottomSheet
import com.example.gastion.ui.theme.components.Loading

abstract class BaseActivity<screens : BaseUiState> :
  ComponentActivity() {

  protected abstract val viewModel: BaseViewModel<screens>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MainScreen()

      MessageScreen()

      EventsObserver()
    }
  }

  @Composable
  abstract fun MainScreen()

  abstract fun eventHandler(events: Any)

  @Composable
  private fun MessageScreen() {
    LoadingContent()

    ToastMessage()

    AlertSheet()
  }

  @Composable
  open fun LoadingContent() {
    val data by viewModel.messageState.collectAsState()
    if (data.isLoading)
      Loading(isDismissible = true)
  }

  @Composable
  open fun ToastMessage() {
    val data by viewModel.messageState.collectAsState()
    val toastData = data.toastData ?: return
    Toast.makeText(this, stringResource(id = toastData), Toast.LENGTH_LONG)
      .show()
    viewModel.onToastShown()
  }

  @Composable
  open fun AlertSheet() {
    val data by viewModel.messageState.collectAsState()
    AlertBottomSheet(data.sheetData, viewModel::dismissBottomSheet)
  }

  @Composable
  open fun EventsObserver() {
    LaunchedEffect(lifecycle) {
      eventHandler("")
    }
  }

}