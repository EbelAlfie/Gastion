package com.example.gastion.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
  bodyLarge = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp
  )
  /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

internal object TypographyToken {
  val FontSmallBold: TextStyle = TextStyle(
    color = Color.Black,
    fontSize = TextSize.sizeSmall,
    fontWeight = FontWeight.Bold
  )
  val FontSmallNormal: TextStyle = TextStyle(
    color = Color.Black,
    fontSize = TextSize.sizeSmall,
    fontWeight = FontWeight.Normal
  )
  val FontMediumBold: TextStyle = TextStyle(
    color = Color.Black,
    fontSize = TextSize.sizeMedium,
    fontWeight = FontWeight.Bold
  )
  val FontMediumNormal: TextStyle = TextStyle(
    color = Color.Black,
    fontSize = TextSize.sizeMedium,
    fontWeight = FontWeight.Normal
  )
  val FontBigBold: TextStyle = TextStyle(
    color = Color.Black,
    fontSize = TextSize.sizeBig,
    fontWeight = FontWeight.Bold
  )
  val FontBigNormal: TextStyle = TextStyle(
    color = Color.Black,
    fontSize = TextSize.sizeBig,
    fontWeight = FontWeight.Normal
  )
}