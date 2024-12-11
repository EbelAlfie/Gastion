package com.example.gastion.ui.theme.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.gastion.ui.theme.Red

object PositiveButtonDefaults {
  val shape: Shape = RoundedCornerShape(5.dp)
  val border: BorderStroke = BorderStroke(1.dp, Red)

  @Composable
  fun buttonColor(): ButtonColors =
    ButtonDefaults.buttonColors(
      containerColor = Red,
      contentColor = Color.White,
    )
}

object NegativeButtonDefaults {
  val shape: Shape = RoundedCornerShape(5.dp)
  val border: BorderStroke = BorderStroke(1.dp, Red)

  @Composable
  fun buttonColor(): ButtonColors =
    ButtonDefaults.buttonColors(
      containerColor = Red,
      contentColor = Color.White,
    )
}

@Composable
fun PrimaryGasButton(
  modifier: Modifier = Modifier,
  label: String,
  onClick: () -> Unit,
) {
  val buttonDefaults = PositiveButtonDefaults
  Button(
    modifier = modifier,
    shape = buttonDefaults.shape,
    colors = buttonDefaults.buttonColor(),
    border = buttonDefaults.border,
    onClick = onClick
  ) {
    Text(label)
  }
}

@Composable
fun SecondaryGasButton(
  modifier: Modifier = Modifier,
  label: String,
  onClick: () -> Unit,
) {
  val buttonDefaults = NegativeButtonDefaults
  Button(
    modifier = modifier,
    shape = buttonDefaults.shape,
    colors = buttonDefaults.buttonColor(),
    border = buttonDefaults.border,
    onClick = onClick
  ) {
    Text(label)
  }
}