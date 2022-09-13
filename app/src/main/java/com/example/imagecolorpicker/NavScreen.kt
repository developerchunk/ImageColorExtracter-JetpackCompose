package com.example.imagecolorpicker

import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.imagecolorpicker.PaletteGenerator.convertImageUrlToBitmap
import com.example.imagecolorpicker.PaletteGenerator.extractColorsFromBitmap

@Composable
fun NavScreen(
    navController: NavController,
    paletteViewModel: PaletteViewModel
) {

    val colorPalette by paletteViewModel.colorPalette
    val context = LocalContext.current

    var launchedEffectTriggered by remember { mutableStateOf(false) }

    val imageUrl by paletteViewModel.imageUrl

    LaunchedEffect(key1 = true) {
        try {
            val bitmap = convertImageUrlToBitmap(
                imageUrl = imageUrl,
                context = context
            )
            if (bitmap != null) {
                launchedEffectTriggered = true
                paletteViewModel.setColorPalette(
                    colors = extractColorsFromBitmap(
                        bitmap = bitmap
                    )
                )
            }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    if (colorPalette.isNotEmpty() && launchedEffectTriggered) {
        PaletteMainScreen(
            paletteViewModel = paletteViewModel,
            colors = colorPalette,
            imageUrl = imageUrl,
            navController = navController
        )
    }

}