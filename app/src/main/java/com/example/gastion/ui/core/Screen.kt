package com.example.gastion.ui.core

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
inline fun <reified screen: BaseUiState> Screen(
  model: BaseUiState,
  crossinline content: @Composable (screen) -> Unit
) {
  val transitionState = remember { MutableTransitionState(model is screen) }
  AnimatedVisibility(
    visibleState = transitionState
  ) {
    (model as? screen)?.let { content(it) }
  }
}