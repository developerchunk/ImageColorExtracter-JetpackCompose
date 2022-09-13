package com.example.imagecolorpicker

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(
    navController: NavHostController,
    paletteViewModel: PaletteViewModel
) {

    NavHost(navController = navController, startDestination = Screens.NavScreen.route) {
        composable(route = Screens.NavScreen.route) {
            NavScreen(navController = navController, paletteViewModel = paletteViewModel)
        }
    }

}