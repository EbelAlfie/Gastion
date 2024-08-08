package com.example.gastion.ui.theme.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsIgnoringVisibility
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
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
import com.example.gastion.ui.component.BottomSheetData
import com.example.gastion.ui.component.BottomSheetErrorContent

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AlertBottomSheet(
  data: BottomSheetData?,
  onDismiss: () -> Unit
) {
  val sheetState = rememberModalBottomSheetState(
    skipPartiallyExpanded = true,
    confirmValueChange = {
      if (it == SheetValue.Hidden) onDismiss.invoke()
      true
    })

  LaunchedEffect(data) {
    if (data == null) sheetState.hide()
    else sheetState.show()
  }

  BackHandler(data != null, onDismiss)
  data?.let {
    ModalBottomSheet(
      sheetState = sheetState,
      onDismissRequest = onDismiss
    ) {
      BottomSheetErrorContent(it)
      Spacer(
        modifier = Modifier.windowInsetsBottomHeight(
          WindowInsets.navigationBarsIgnoringVisibility
        )
      )
    }
  }

}

@Composable
fun DefaultSheetContent(data: BottomSheetData?) {
  data?.let {
    Column(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Image(painter = painterResource(id = R.drawable.ic_gas_station), contentDescription = null)
      Spacer(modifier = Modifier.height(16.dp))
      Text(text = stringResource(id = it.title))
      Spacer(modifier = Modifier.height(8.dp))
      Text(text = stringResource(id = it.content))
      Spacer(modifier = Modifier.height(16.dp))
      Button(onClick = { }) {
        Text(text = stringResource(id = R.string.label_close))
      }
    }
  }

}