package com.example.gastion.ui.component

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.AnimationConstants
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.gastion.ui.theme.Grey_Alpha

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun <contentData>BottomSheetContainer(
  sheetState: BottomSheetState<contentData>,
  content: @Composable (BottomSheetState<contentData>) -> Unit
) {
  AnimatedVisibility(
    visible = sheetState.isBottomSheetVisible,
    enter = fadeIn(animationSpec = tween(delayMillis = AnimationConstants.DefaultDurationMillis / 3)),
    exit = fadeOut(animationSpec = tween(delayMillis = AnimationConstants.DefaultDurationMillis / 3))
  ) {
    BackHandler(sheetState.isBottomSheetVisible) {
      if (sheetState.isDismisable) sheetState.dismissBottomSheet()
    }
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(Grey_Alpha)
        .clickable(
          onClick = {
            if (sheetState.isDismisable) sheetState.dismissBottomSheet()
          },
          indication = null,
          interactionSource = remember { MutableInteractionSource() },
        ),
    ) {
      val modifier = Modifier.align(Alignment.BottomCenter)
      Card(
        modifier = modifier
          .fillMaxWidth()
          .background(Color.White, RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
          .clickable(
            indication = null,
            onClick = {},
            interactionSource = remember { MutableInteractionSource() },
          )
          .animateEnterExit(
            enter = slideInVertically(
              initialOffsetY = { height -> height },
              animationSpec = tween(),
            ),
            exit = slideOutVertically(
              targetOffsetY = { height -> height },
              animationSpec = tween(delayMillis = AnimationConstants.DefaultDurationMillis / 2),
            ),
          ),
        elevation = CardDefaults.elevatedCardElevation(1.dp)
      ) {
        content.invoke(sheetState)
      }

    }
  }

}

@Composable
fun BottomSheetErrorContent(
  sheetData: BottomSheetData,
  dismissRequest: (() -> Unit)? = null
) {
  Column(
    modifier = Modifier
      .background(Color.White)
      .fillMaxWidth()
      .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Bottom
  ) {
    Image(
      modifier = Modifier
        .height(200.dp)
        .width(200.dp),
      contentScale = ContentScale.Fit,
      painter = painterResource(id = sheetData.image),
      contentDescription = null
    )
    Text(
      modifier = Modifier.padding(12.dp),
      text = stringResource(id = sheetData.title),
      fontWeight = FontWeight.Bold
    )
    Text(
      modifier = Modifier.padding(12.dp),
      text = stringResource(id = sheetData.content),
      textAlign = TextAlign.Center
    )
  }
}
