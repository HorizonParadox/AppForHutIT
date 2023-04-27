package com.example.sportappforhuntit.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sportappforhuntit.MainViewModel
import com.example.sportappforhuntit.navigation.Screens
import com.example.sportappforhuntit.ui.theme.GreyBackground
import com.example.sportappforhuntit.ui.theme.TextColor
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, viewModel: MainViewModel){
  var startAnimate by remember {
    mutableStateOf(false)
  }
  val alphaAnimation = animateFloatAsState(
    targetValue = if (startAnimate) 1f else 0f,
    animationSpec = tween(durationMillis = 3000)
  )
  LaunchedEffect(key1 = true){
    startAnimate = true
    viewModel.getAllMatches()
    delay(4000)
    navController.navigate(Screens.Main.route)
  }
  Splash(alpha = alphaAnimation.value)
}

@Composable
fun Splash(alpha: Float){
  Box(
    modifier = Modifier.fillMaxSize().background(GreyBackground),
    contentAlignment = Alignment.Center
  ){
    Icon(
      modifier = Modifier
        .size(128.dp)
        .alpha(alpha)
        .padding(8.dp),
      imageVector = ImageVector.vectorResource(id = com.example.sportappforhuntit.R.drawable.gamepad_solid),
      contentDescription = null,
      tint = TextColor
    )
  }
}