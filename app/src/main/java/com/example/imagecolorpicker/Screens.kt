package com.example.imagecolorpicker

sealed class Screens(val route: String) {
    object NavScreen: Screens(route = "nav_screen")
}
