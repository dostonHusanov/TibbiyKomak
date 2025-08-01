package com.doston.tibbiykomak.navigation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.doston.tibbiykomak.auth.LanguageScreen
import com.doston.tibbiykomak.auth.LocaleManager
import com.doston.tibbiykomak.auth.RegisterScreen
import com.doston.tibbiykomak.home.HomeScreen
import com.doston.tibbiykomak.home.SecondHomeScreen
import com.doston.tibbiykomak.onBoarding.OnBoardingScreen
import com.doston.tibbiykomak.onBoarding.WelcomeScreen
import com.doston.tibbiykomak.reminder.ReminderScreen
import com.doston.tibbiykomak.ui.theme.ThemeViewModel

@SuppressLint("RememberReturnType")
@Composable
fun MainNav(context: Context,viewModel: ThemeViewModel) {
    val navController = rememberNavController()

    // Check if the user has completed the onboarding process
    val hasCompletedOnboarding = remember { mutableStateOf(hasCompletedOnboarding(context)) }

    NavHost(
        navController = navController,
        startDestination = if (hasCompletedOnboarding.value) "main" else "welcome"
    ) {
        // First Navigation Flow (Welcome, Language, and Onboarding)
        composable("welcome") {
            WelcomeScreen(onStart = {
                navController.navigate("language")
            })
        }
        composable("language") {
            val localContext = LocalContext.current
            val activity = localContext as? Activity

            LanguageScreen(
                onLanguageSelected = { languageCode ->
                    LocaleManager.setLocale(localContext, languageCode)
                    LocaleManager.getPrefs(localContext)
                        .edit()
                        .putBoolean("language_selected", true)
                        .apply()
                    activity?.recreate()
                },
                onNextClicked = {
                    navController.navigate("onboarding") {
                        popUpTo("language") { inclusive = true }
                    }
                },
                viewModel = viewModel
            )
        }




        composable("onboarding") {
            OnBoardingScreen(onFinish = {
                // Set the flag indicating onboarding is complete
                navController.navigate("registerScreen")
            })
        }
        composable("registerScreen"){
            RegisterScreen( onFinish = {
                hasCompletedOnboarding.value = true
                setOnboardingCompleted(context, true)
                navController.navigate("main")
            })
        }

        composable("main") {
            SecondaryNav(viewModel =viewModel ) // Main content screen for the quiz
        }

        composable("homeScreen") {
            HomeScreen(navController = navController,1, viewModel = viewModel)
        }

        composable("reminderScreen") { backStackEntry ->
            ReminderScreen(navController,viewModel)
        }


    }
}

fun hasCompletedOnboarding(context: Context): Boolean {
    val sharedPreferences = context.getSharedPreferences("appPrefs", Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean("hasCompletedOnboarding", false)
}

fun setOnboardingCompleted(context: Context, value: Boolean) {
    val sharedPreferences = context.getSharedPreferences("appPrefs", Context.MODE_PRIVATE)
    sharedPreferences.edit().putBoolean("hasCompletedOnboarding", value).apply()
}