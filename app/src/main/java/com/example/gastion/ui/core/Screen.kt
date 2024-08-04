package com.example.gastion.ui.core

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun Screen(
  model: BaseScreenModel,
  content: @Composable () -> Unit
) {
  val transitionState = remember { MutableTransitionState(true) }
  AnimatedVisibility(
    visibleState = transitionState
  ) {
    content()
  }
}