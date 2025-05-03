package com.doston.tibbiykomak.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ContactSupport
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.doston.tibbiykomak.R
import com.doston.tibbiykomak.home.AboutScreen
import com.doston.tibbiykomak.home.ContactScreen
import com.doston.tibbiykomak.home.HomeScreen
import com.doston.tibbiykomak.reminder.ReminderScreen
import kotlinx.coroutines.launch

data class BottomNavItem(
    val route: String,
    val title: String,
    val icon: @Composable () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondaryNav() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    val bottomNavItems = listOf(
        BottomNavItem(
            route = "homeScreen",
            title = "Home",
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") }
        ),
        BottomNavItem(
            route = "reminderScreen",
            title = "Reminder",
            icon = { Icon(Icons.Default.Notifications, contentDescription = "Reminder") }
        )
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(12.dp))
                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Card(
                            shape = CircleShape,
                            modifier = Modifier.padding(10.dp),
                            colors = CardDefaults.cardColors(Color.Red)
                        ) {
                            Card(
                                shape = CircleShape,
                                modifier = Modifier.padding(3.dp),
                                colors = CardDefaults.cardColors(Color.Cyan)
                            ) {
                                Image(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "Profile image",
                                    modifier = Modifier.size(60.dp).padding(6.dp)
                                )
                            }
                        }
                        var isDarkMode by remember { mutableStateOf(true) }

                        Icon(
                            painter = painterResource(
                                if (isDarkMode) R.drawable.night_mode else R.drawable.light_mode
                            ),
                            contentDescription = "Mode",
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(Color.White) // Optional: background to see shadow properly
                                .shadow(0.dp, shape = CircleShape)
                                .clickable { isDarkMode = !isDarkMode }
                        )
                    }

                    Text("Doston Husanov", modifier = Modifier.padding(horizontal = 16.dp), fontSize = 16.sp)
                    Text("18 yosh", modifier = Modifier.padding(horizontal = 16.dp), fontSize = 16.sp)
                    Text("Erkak", modifier = Modifier.padding(horizontal = 16.dp), fontSize = 16.sp)

                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                    NavigationDrawerItem(
                        label = { Text("Ilova Haqida") },
                        selected = false,
                        icon = { Icon(Icons.Outlined.Info, contentDescription = null) },
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate("aboutScreen")

                        }
                    )

                    NavigationDrawerItem(
                        label = { Text("Bog'lanish") },
                        selected = false,
                        icon = { Icon(Icons.Outlined.Phone, contentDescription = null) },
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate("contactScreen")
                        }
                    )

                    Spacer(Modifier.height(12.dp))
                }
            }
        }
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        // Show bottom bar only for these routes
        val isBottomBarVisible = currentRoute in listOf("homeScreen", "reminderScreen")

        if (isBottomBarVisible) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "Tibbiy Ko'mak",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    if (drawerState.isClosed) drawerState.open() else drawerState.close()
                                }
                            }) {
                                Icon(Icons.Default.Menu, contentDescription = "Menu")
                            }
                        }
                    )
                },
                bottomBar = {
                    NavigationBar(containerColor = Color.Cyan) {
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
                                label = { Text(text = item.title) },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = Color.Black,
                                    unselectedIconColor = Color.Magenta,
                                    selectedTextColor = Color.Black,
                                    unselectedTextColor = Color.Magenta
                                )
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
                        HomeScreen(navController, userName = "Dostonbek", categoryId = 1)
                    }
                    composable("reminderScreen") {
                        ReminderScreen()
                    }
                    composable("aboutScreen") { AboutScreen(navController) }
                    composable("contactScreen") { ContactScreen(navController) }

                }
            }
        } else {
            // Fullscreen content for drawer navigation
            NavHost(navController = navController, startDestination = "homeScreen") {
                composable("aboutScreen") { AboutScreen(navController) }
                composable("contactScreen") { ContactScreen(navController) }

                // Include other screens for back navigation if needed
                composable("homeScreen") {
                    HomeScreen(navController, userName = "Dostonbek", categoryId = 1)
                }
                composable("reminderScreen") {
                    ReminderScreen()
                }
            }
        }
    }
}
