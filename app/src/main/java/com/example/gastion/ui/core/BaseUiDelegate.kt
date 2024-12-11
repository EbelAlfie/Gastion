//package com.example.gastion.ui.core
//
//import androidx.activity.viewModels
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import com.example.gastion.ui.core.BaseUiDelegate.MainScope
//import com.example.gastion.ui.core.BaseUiDelegate.MainVM
//import com.example.gastion.ui.main.MainScreens
//import com.example.gastion.ui.main.MainScreens.Login
//import com.example.gastion.ui.main.MainScreens.Maps
//import com.example.gastion.ui.screens.gasmap.GasMapScreen
//import com.example.gastion.ui.screens.login.LoginScreen
//import com.example.gastion.ui.theme.GastionTheme
//
//interface BaseUiDelegate {
//  interface MainVM {
//    fun MainScope.Login.login()
//
//    fun MainScope.Login.onNameChanged(newName: String)
//
//    fun MainScope.Login.onPassChanged(newPass: String)
//
//    fun MainScope.Maps.requestLocationUpdate()
//  }
//
//  interface MainScope {
//    object Login
//    object Maps
//  }
//}
//
//class TestViewModel: BaseViewModel<MainScreens>(), MainVM {
//  override fun getInitialUiState(): MainScreens = MainScreens.Login()
//
//  override fun MainScope.Login.login() {}
//
//  override fun MainScope.Login.onNameChanged(newName: String) {}
//  override fun MainScope.Login.onPassChanged(newPass: String) {}
//
//  override fun MainScope.Maps.requestLocationUpdate() {}
//
//}
//
//class TestActivity: BaseActivity<MainScreens, BaseUiEvents>() {
//
//  override val viewModel: TestViewModel by viewModels()
//
//  @Composable
//  fun LoginPage(
//    uiState : MainScreens.Login,
//    onLogin: MainScope.Login.() -> Unit,
//    onNameChange : MainScope.Login.(String) -> Unit,
//    onPassChange : MainScope.Login.(String) -> Unit,
//  ) {
//
//  }
//
//  @Composable
//  override fun MainScreen() {
//    GastionTheme {
//      val screen by viewModel.uiState.collectAsState()
//
//      Screen<Login>(screen) {
//        LoginPage(
//          uiState = screen as MainScreens.Login,
//          onLogin = { viewModel.MainScope.Login.login() },
//          onNameChange = { name -> viewModel.MainScope.Login.onNameChanged(name) },
//          onPassChange = { pass -> viewModel.MainScope.Login.onPassChanged(pass) }
//        )
//      }
//
//      Screen<Maps>(screen) {
//        GasMapScreen(
//          uiState = it,
//          requestLocationUpdate = viewModel::requestLocationUpdate
//        )
//      }
//    }
//  }
//
//  override fun eventHandler(events: Any) {}
//
//}