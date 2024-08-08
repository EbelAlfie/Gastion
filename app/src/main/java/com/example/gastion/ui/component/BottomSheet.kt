package com.example.gastion.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun BottomSheetErrorContent(
  sheetData: BottomSheetData?,
  dismissRequest: (() -> Unit)? = null
) {
  sheetData?.let {
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
        painter = painterResource(id = it.image),
        contentDescription = null
      )
      Text(
        modifier = Modifier.padding(12.dp),
        text = stringResource(id = it.title),
        fontWeight = FontWeight.Bold
      )
      Text(
        modifier = Modifier.padding(12.dp),
        text = stringResource(id = it.content),
        textAlign = TextAlign.Center
      )


    }

  }
}
