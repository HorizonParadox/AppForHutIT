package com.example.sportappforhuntit.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sportappforhuntit.MainViewModel
import com.example.sportappforhuntit.screens.DetailsScreen
import com.example.sportappforhuntit.screens.MainScreen
import com.example.sportappforhuntit.screens.MyWebView
import com.example.sportappforhuntit.screens.SplashScreen
import com.example.sportappforhuntit.utils.Constants

sealed class Screens(val route: String) {
    object Splash : Screens(route = Constants.Screens.SPLASH_SCREEN)
    object Main : Screens(route = Constants.Screens.MAIN_SCREEN)
    object Details : Screens(route = Constants.Screens.DETAILS_SCREEN)
    object Web : Screens(route = Constants.Screens.WEBVIEW_SCREEN)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavHost(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route
    ) {

        composable(route = Screens.Splash.route) {
            SplashScreen(navController = navController, viewModel = viewModel)
        }

        composable(route = Screens.Main.route) {
            MainScreen(navController = navController, viewModel = viewModel)
        }

        composable(route = Screens.Details.route) {
            DetailsScreen(viewModel = viewModel)

        }
        composable(route = Screens.Web.route) {
            MyWebView()
        }
    }
}