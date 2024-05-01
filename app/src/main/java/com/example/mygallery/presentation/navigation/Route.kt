package com.example.mygallery.presentation.navigation

sealed class Route(val route: String) {
    data object Login : Route("login")
    data object Register : Route("register")
    data object Home : Route("home")
    data object Error: Route("error")
}