package com.example.gastion.ui.theme.components

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gastion.R

data class SheetData(
  @DrawableRes val image: Int,
  @StringRes val title: Int,
  @StringRes val message: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertBottomSheet(
  data: SheetData?,
  onDismiss: () -> Unit
) {
  val sheetState = rememberModalBottomSheetState(false)

  LaunchedEffect(key1 = data) {
    if (data == null) sheetState.hide()
    else sheetState.show()
  }

  BackHandler(data != null, onDismiss)
  ModalBottomSheet(onDismissRequest = onDismiss) {
    DefaultSheetContent(data)
  }
}

@Composable
fun DefaultSheetContent(data: SheetData?) {
  data?.let {
    Column (
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Image(painter = painterResource(id = R.drawable.ic_gas_station), contentDescription = null)
      Spacer(modifier = Modifier.height(16.dp))
      Text(text = stringResource(id = it.title))
      Spacer(modifier = Modifier.height(8.dp))
      Text(text = stringResource(id = it.message))
      Spacer(modifier = Modifier.height(16.dp))
      Button(onClick = { }) {
        Text(text = stringResource(id = R.string.label_close))
      }
    }
  }

}