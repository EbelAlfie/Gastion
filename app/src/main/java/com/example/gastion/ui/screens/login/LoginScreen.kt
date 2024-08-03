package com.example.gastion.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.gastion.R
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
  screenData: Login,
  onNameChange: (String) -> Unit,
  onPassChange: (String) -> Unit,
  onLogin: () -> Unit
) {
  Scaffold {
    Column(
      modifier = Modifier.padding(it),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.SpaceAround
    ) {
      Text(
        text = stringResource(id = R.string.login_title),
        style = TypographyToken.FontBigBold
      )

      TextInput(
        value = screenData.name,
        hint = stringResource(id = R.string.usn_hint),
        onValueChange = onNameChange
      )

      TextInput(
        value = screenData.password,
        hint = stringResource(id = R.string.password_hint),
        onValueChange = onPassChange
      )

      PrimaryGasButton(
        label = stringResource(id = R.string.submit_label),
        onClick = onLogin
      )
    }
  }
}