package com.doston.tibbiykomak.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.doston.tibbiykomak.home.HomeScreen
import com.doston.tibbiykomak.reminder.ReminderScreen

data class BottomNavItem(
    val route: String,
    val title: String,
    val icon: @Composable () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondaryNav() {
    val navController = rememberNavController()
    val bottomNavItems = listOf(
        BottomNavItem(
            route = "homeScreen",
            title = "Home",
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home") }
        ),
        BottomNavItem(
            route = "reminderScreen",
            title = "Reminder",
            icon = {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Reminder"
                )
            }
        )
    )

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "Tibbiy Ko'mak",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Red // light gray or your custom color
            ),modifier = Modifier.height(56.dp)
        )
    },

        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            NavigationBar {
                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = item.icon,
                        label = { Text(text = item.title) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "homeScreen",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("homeScreen") {
                HomeScreen(
                    navController = navController,
                    userName = "Dostonbek",
                    categoryId = 1
                )
            }
            composable("reminderScreen") {
                ReminderScreen()
            }
        }
    }
}
