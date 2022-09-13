package com.example.imagecolorpicker

import android.graphics.Color.parseColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.imagecolorpicker.Constants.IMAGE_1
import com.example.imagecolorpicker.Constants.IMAGE_2
import com.example.imagecolorpicker.Constants.IMAGE_3
import com.example.imagecolorpicker.Constants.IMAGE_4
import com.example.imagecolorpicker.Constants.IMAGE_5
import com.example.imagecolorpicker.Constants.LOREM
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun PaletteMainScreen(
    paletteViewModel: PaletteViewModel,
    colors: Map<String, String>,
    imageUrl: String,
    navController: NavController
) {
    val systemController = rememberSystemUiController()

    var vibrant by remember { mutableStateOf("#000000") }
    var darkVibrant by remember { mutableStateOf("#000000") }
    var lightVibrant by remember { mutableStateOf("#000000") }
    var domainSwatch by remember { mutableStateOf("#000000") }
    var mutedSwatch by remember { mutableStateOf("#000000") }
    var lightMutedSwatch by remember { mutableStateOf("#000000") }
    var darkMutedSwatch by remember { mutableStateOf("#000000") }
    var onDarkVibrant by remember { mutableStateOf("#ffffff") }

    val brushColor: List<Color> =
        listOf(Color(parseColor(vibrant)).copy(alpha = 0.5f), Color.Transparent)

    // get the colors from the Map
    LaunchedEffect(key1 = true) {
        vibrant = colors["vibrant"]!!
        darkVibrant = colors["darkVibrant"]!!
        lightVibrant = colors["lightVibrant"]!!
        domainSwatch = colors["domainSwatch"]!!
        mutedSwatch = colors["mutedSwatch"]!!
        lightMutedSwatch = colors["lightMuted"]!!
        darkMutedSwatch = colors["darkMuted"]!!
        onDarkVibrant = colors["onDarkVibrant"]!!
    }

    systemController.setSystemBarsColor(
        color = Color(parseColor(darkMutedSwatch))
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            model = imageUrl,
            contentDescription = "image_url",
            contentScale = ContentScale.FillBounds
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(parseColor(darkMutedSwatch)))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = brushColor,
                            startY = 30f,
                            endY = 230f
                        )
                    )
            )

            Text(
                modifier = Modifier.padding(top = 40.dp, start = 20.dp, end = 20.dp),
                text = LOREM,
                color = Color(
                    parseColor(onDarkVibrant)
                )
            )

            Button(
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .width(150.dp)
                    .height(45.dp)
                    .align(alignment = Alignment.BottomCenter),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(parseColor(vibrant))),
                onClick = {
                    navController.popBackStack()
                    when (imageUrl) {
                        IMAGE_1 -> {
                            paletteViewModel.imageUrl.value = IMAGE_2
                            navController.navigate(route = Screens.NavScreen.route)
                        }
                        IMAGE_2 -> {
                            paletteViewModel.imageUrl.value = IMAGE_3
                            navController.navigate(route = Screens.NavScreen.route)
                        }
                        IMAGE_3 -> {
                            paletteViewModel.imageUrl.value = IMAGE_4
                            navController.navigate(route = Screens.NavScreen.route)
                        }
                        IMAGE_4 -> {
                            paletteViewModel.imageUrl.value = IMAGE_5
                            navController.navigate(route = Screens.NavScreen.route)
                        }
                        IMAGE_5 -> {
                            paletteViewModel.imageUrl.value = IMAGE_1
                            navController.navigate(route = Screens.NavScreen.route)
                        }
                    }
                }
            ) {
                Text(text = "NEXT", color = Color(parseColor(onDarkVibrant)), fontSize = 16.sp)
            }
        }

    }

}












