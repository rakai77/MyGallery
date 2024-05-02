package com.example.mygallery.presentation

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mygallery.presentation.error.ErrorScreen
import com.example.mygallery.presentation.home.HomeScreen
import com.example.mygallery.presentation.login.LoginScreen
import com.example.mygallery.presentation.navigation.Route
import com.example.mygallery.presentation.register.RegisterScreen
import com.example.mygallery.presentation.theme.MyGalleryTheme
import com.example.mygallery.utils.EmptyStateModel
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition { !viewModel.isSplashFinished.value }
        }
        setContent {
            MyGalleryTheme {
                GalleryApp(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalMaterialApi::class)
@Composable
fun GalleryApp(viewModel: MainViewModel = hiltViewModel()) {
    val sheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    val bottomSheerNavigator = remember { BottomSheetNavigator(sheetState) }
    val navController = rememberNavController(bottomSheerNavigator)


    val isUserLoggedIn by viewModel.isUserLoggedIn.collectAsState()

    isUserLoggedIn?.let { isLoggedIn ->
        NavHost(
            navController = navController,
            startDestination = if (isLoggedIn) Route.Home.route else Route.Login.route
        ) {
            composable(
                route = Route.Login.route
            ) {
                LoginScreen(
                    viewModel = hiltViewModel(),
                    onNavigateToHome = {
                        navController.navigate(Route.Home.route)
                    },
                    onNavigateToRegister = {
                        navController.navigate(Route.Register.route)
                    },
                    onLoginError = {
                        val json = Uri.encode(Gson().toJson(it))
                        navController.navigate("${Route.Error.route}/$json")
                    }
                )
            }

            composable(
                route = Route.Register.route
            ) {
                RegisterScreen(
                    viewModel = hiltViewModel(),
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onNavigateToHome = {
                        navController.navigate(Route.Home.route)
                    },
                    onRegisterError = {
                        val json = Uri.encode(Gson().toJson(it))
                        navController.navigate("${Route.Error.route}/$json")
                    }
                )
            }

            composable(
                route = Route.Home.route
            ) {
                HomeScreen(viewModel = hiltViewModel())
            }

            bottomSheet(
                route = "${Route.Error.route}/{empty-params}",
                arguments = listOf(
                    navArgument("empty-params") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val emptyParams = backStackEntry.arguments?.getString("empty-params")
                ErrorScreen(
                    Gson().fromJson(emptyParams, EmptyStateModel::class.java)
                ) {
                    navController.popBackStack()
                }
            }

        }
    }
}