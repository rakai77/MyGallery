package com.example.mygallery.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mygallery.presentation.login.LoginScreen
import com.example.mygallery.presentation.navigation.Route
import com.example.mygallery.presentation.theme.MyGalleryTheme
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
                GalleryApp()
            }
        }
    }
}

@Composable
fun GalleryApp() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.Login.route) {
        composable(
            route = Route.Login.route
        ) {
            LoginScreen()
        }
    }

}