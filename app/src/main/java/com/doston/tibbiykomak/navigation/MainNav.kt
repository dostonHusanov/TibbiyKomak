package com.doston.tibbiykomak.navigation

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.doston.tibbiykomak.auth.RegisterScreen
import com.doston.tibbiykomak.home.HomeScreen
import com.doston.tibbiykomak.home.SecondHomeScreen
import com.doston.tibbiykomak.onBoarding.OnBoardingScreen
import com.doston.tibbiykomak.onBoarding.WelcomeScreen
import com.doston.tibbiykomak.reminder.ReminderScreen

@SuppressLint("RememberReturnType")
@Composable
fun MainNav(context: Context) {
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
                navController.navigate("onboarding")
            })
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
            SecondaryNav() // Main content screen for the quiz
        }

        composable("homeScreen") {
            HomeScreen(navController = navController,"Dostonbek",1)
        }

        composable("reminderScreen") { backStackEntry ->
            ReminderScreen(navController)
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