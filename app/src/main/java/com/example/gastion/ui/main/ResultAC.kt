package com.example.gastion.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.gastion.ui.theme.GastionTheme

class ResultAC: ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      GastionTheme {
        GasMap(
          modifier = Modifier.fillMaxSize()
        )
        setResult(
          RESULT_OK,
          Intent(this, MainActivity::class.java)
            .putExtra("Anjing", true)
          )
        finish()
      }
    }
  }

}