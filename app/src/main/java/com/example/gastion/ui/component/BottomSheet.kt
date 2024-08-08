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
import com.example.gastion.ui.theme.components.PrimaryGasButton
import com.example.gastion.ui.theme.components.SecondaryGasButton

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

    PrimaryGasButton(
      label = stringResource(id = sheetData.positiveLabel),
      onClick = { sheetData.onPositiveClicked?.invoke() }
    )

    sheetData.negativeLabel?.let {
      SecondaryGasButton(
        label = stringResource(id = it),
        onClick = { sheetData.onNegativeClicked?.invoke()}
      )
    }
  }


}
