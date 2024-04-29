package com.example.gastion.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gastion.ui.theme.GastionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      GastionTheme {
        val launcher = rememberLauncherForActivityResult(
          contract = ActivityResultContracts.StartActivityForResult()
        ) {
          Log.d("RESULT", it.toString())
        }
        GasMap(
          modifier = Modifier.fillMaxSize()
        )
        Button(onClick = {
          val toIntent = Intent(this, ResultAC::class.java)
          launcher.launch(toIntent)
        }) {
         Text("OI ke sini")
        }
      }
    }
  }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Text(
    text = "Hello $name!",
    modifier = modifier
  )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  GastionTheme {
    Greeting("Android")
  }
}