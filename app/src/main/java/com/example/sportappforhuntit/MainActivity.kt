package com.example.sportappforhuntit

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.sportappforhuntit.navigation.SetupNavHost
import com.example.sportappforhuntit.ui.theme.SportAppForHuntITTheme
import com.example.sportappforhuntit.utils.Constants
import com.onesignal.OneSignal
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class  MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(this)
        OneSignal.setAppId(Constants.OneSignalId.ONESIGNAL_APP_ID)
        OneSignal.promptForPushNotifications()

        setContent {
            SportAppForHuntITTheme {
                val navController = rememberNavController()
                val viewModel = hiltViewModel<MainViewModel>()
                SetupNavHost(navController = navController, viewModel= viewModel)
            }
        }
    }

}