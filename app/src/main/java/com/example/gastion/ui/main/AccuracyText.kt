package com.example.gastion.ui.main

import android.location.Location
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.gastion.R
import com.example.gastion.R.drawable
import com.example.gastion.ui.theme.IconPadding
import com.example.gastion.ui.theme.NormalPadding

@Composable
fun AccuracyText(
  modifier: Modifier = Modifier,
  location: Location?
) {
  Box {
    Row (
      modifier = modifier.padding(NormalPadding)
    ){
      Image(
        modifier = Modifier.padding(IconPadding),
        painter = painterResource(id = drawable.ic_locatoin_accuracy),
        contentDescription = null
      )
      Text(
        modifier = Modifier.padding(IconPadding),
        text = stringResource(id = R.string.location_accuracy_label, location?.accuracy ?: 0.0)
      )
    }
  }
}