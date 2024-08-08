package com.example.gastion.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gastion.R
import com.example.gastion.ui.main.MainScreens
import com.example.gastion.ui.theme.TypographyToken
import com.example.gastion.ui.theme.components.PrimaryGasButton
import com.example.gastion.ui.theme.components.TextInput

/**
 * Screen:
 *
 * This is the login page.
 * by definition, screen is equivalent to the layout file in the xml
 * therefore, this screen is the layout for login page
 */
@Composable
fun LoginScreen(
  uiState: MainScreens.Login,
  onNameChange: (String) -> Unit,
  onPassChange: (String) -> Unit,
  onLogin: () -> Unit
) {
  Scaffold(
    modifier = Modifier.fillMaxSize()
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(it)
        .padding(horizontal = 16.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Text(
        text = stringResource(id = R.string.login_title),
        style = TypographyToken.FontBigBold
      )

      Spacer(modifier = Modifier.height(20.dp))

      TextInput(
        value = uiState.userName,
        hint = stringResource(id = R.string.usn_hint),
        onValueChange = onNameChange
      )

      Spacer(modifier = Modifier.height(20.dp))

      TextInput(
        value = uiState.password,
        hint = stringResource(id = R.string.password_hint),
        onValueChange = onPassChange
      )

      Spacer(modifier = Modifier.height(20.dp))

      PrimaryGasButton(
        modifier = Modifier.fillMaxWidth(),
        label = stringResource(id = R.string.submit_label),
        onClick = { onLogin() }
      )
    }
  }
}