package com.example.gastion.ui.core

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@Composable
inline fun <reified screen: BaseUiState> Screen(
  model: BaseUiState,
  crossinline content: @Composable (screen) -> Unit
) {
  val transitionState = remember { MutableTransitionState(model is screen) }
  LaunchedEffect(model) {
    transitionState.targetState = model is screen
  }

  AnimatedVisibility(
    visibleState = transitionState,
    enter = fadeIn() + expandVertically(),
    exit = fadeOut() + shrinkVertically()
  ) {
    (model as? screen)?.let { content(it) }
  }
}