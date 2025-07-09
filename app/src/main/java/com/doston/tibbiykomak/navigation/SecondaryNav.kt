package com.doston.tibbiykomak.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Nature
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.doston.tibbiykomak.R
import com.doston.tibbiykomak.data.MainData
import com.doston.tibbiykomak.data.ReminderData
import com.doston.tibbiykomak.data.User
import com.doston.tibbiykomak.database.UserDatabaseHelper
import com.doston.tibbiykomak.home.AboutScreen
import com.doston.tibbiykomak.home.ContactScreen
import com.doston.tibbiykomak.home.HomeScreen
import com.doston.tibbiykomak.home.InfoScreen
import com.doston.tibbiykomak.home.SecondHomeScreen
import com.doston.tibbiykomak.reminder.PillAddScreen
import com.doston.tibbiykomak.reminder.PillEditScreen
import com.doston.tibbiykomak.reminder.PillInfoScreen
import com.doston.tibbiykomak.reminder.PillScreen
import com.doston.tibbiykomak.reminder.ReminderScreen
import com.doston.tibbiykomak.ui.theme.MainColor
import com.doston.tibbiykomak.ui.theme.TextColor
import com.doston.tibbiykomak.ui.theme.TextColor2
import kotlinx.coroutines.launch

data class BottomNavItem(
    val route: String,
    val title: String,
    val icon: @Composable () -> Unit
)

@SuppressLint("StateFlowValueCalledInComposition", "SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondaryNav() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val context = LocalContext.current
    val dbHelper = remember { UserDatabaseHelper(context) }
    var user by remember { mutableStateOf<User?>(null) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    LaunchedEffect(Unit) {
        user = dbHelper.getUser()
    }
    val bottomNavItems = listOf(
        BottomNavItem(
            route = "homeScreen",
            title = "Home",
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") }
        ),
        BottomNavItem(
            route = "secondHomeScreen",
            title = "Second",
            icon = { Icon(Icons.Default.Nature, contentDescription = "") }),
        BottomNavItem(
            route = "reminderScreen",
            title = "Reminder",
            icon = { Icon(Icons.Default.Notifications, contentDescription = "Reminder") }
        )
    )



    ModalNavigationDrawer(gesturesEnabled = currentRoute != "infoScreen",
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(drawerContentColor = TextColor, drawerContainerColor = MainColor) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MainColor)

                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(12.dp))
                    Row(
                        modifier = Modifier
                            .padding(26.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Card(
                            shape = CircleShape,
                            modifier = Modifier.padding(10.dp),
                            colors = CardDefaults.cardColors(TextColor2)
                        ) {
                            Card(
                                shape = CircleShape,
                                modifier = Modifier.padding(3.dp),
                                colors = CardDefaults.cardColors(MainColor)
                            ) {
                                Image(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "Profile image",
                                    modifier = Modifier
                                        .size(60.dp)
                                        .padding(6.dp)
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
                                .background(MainColor) // Optional: background to see shadow properly
                                .shadow(0.dp, shape = CircleShape)
                                .clickable { isDarkMode = !isDarkMode }
                        )
                    }


                    Text(
                        "${user?.name} ${user?.surname}",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        fontSize = 16.sp
                    )
                    Text(
                        "${user?.age} yosh",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        fontSize = 16.sp
                    )
                    Text(
                        "${user?.phoneNumber}",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        fontSize = 16.sp
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                    NavigationDrawerItem(
                        label = { Text("Ilova Haqida") },
                        selected = false,
                        icon = { Icon(Icons.Outlined.Info, contentDescription = null) },
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate("aboutScreen")

                        })
                    Spacer(Modifier.height(10.dp))
                    NavigationDrawerItem(
                        label = { Text("Bog'lanish") },
                        selected = false,

                        icon = { Icon(Icons.Outlined.Phone, contentDescription = null) },
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate("contactScreen")
                        }
                    )


                }
            }
        }
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        // Show bottom bar only for these routes
        val isBottomBarVisible =
            currentRoute in listOf("homeScreen", "secondHomeScreen", "reminderScreen")

        if (isBottomBarVisible) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "Tibbiy Ko'mak",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = TextColor2
                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MainColor,
                            titleContentColor = TextColor
                        ),
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
                    NavigationBar(containerColor = TextColor) {
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
                                    selectedIconColor = MainColor,
                                    unselectedIconColor = MainColor,
                                    selectedTextColor = MainColor,
                                    unselectedTextColor = MainColor,
                                    indicatorColor = Color(0xFF015B22)
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
                        HomeScreen(navController, userName = user?.name.toString(), categoryId = 1)
                    }
                    composable("secondHomeScreen") {
                        SecondHomeScreen(
                            navController,
                            categoryId = 1
                        )
                    }
                    composable("reminderScreen") {
                        ReminderScreen(navController)
                    }
                    composable("pillScreen") {
                        PillScreen(navController)
                    }
                    composable("pillAdd") {
                        PillAddScreen(navController)
                    }

                    composable("aboutScreen") { AboutScreen(navController) }
                    composable("contactScreen") { ContactScreen(navController) }
                    composable("infoScreen") { backStackEntry ->
                        val illness = navController.previousBackStackEntry
                            ?.savedStateHandle?.get<MainData>("illness")
                        illness?.let {
                            InfoScreen(illness = it, navController)
                        }
                    }
                    composable("pillInfo") { backStackEntry ->
                        val pillInfo= navController.previousBackStackEntry
                            ?.savedStateHandle?.get<ReminderData>("pillInfo")
                        pillInfo?.let {
                            PillInfoScreen(data = it, navController)
                        }
                    }
                    composable("pillEdit") { backStackEntry ->
                        val pillEdit= navController.previousBackStackEntry
                            ?.savedStateHandle?.get<ReminderData>("pillEdit")
                        pillEdit?.let {
                            PillEditScreen(pills = it, navController)
                        }
                        if (pillEdit != null) {
                            PillEditScreen(pills = pillEdit,navController)
                        }
                    }
                }
            }
        } else {

            NavHost(navController = navController, startDestination = "homeScreen") {
                composable("aboutScreen") { AboutScreen(navController) }
                composable("contactScreen") { ContactScreen(navController) }

                composable("infoScreen") { backStackEntry ->
                    val illness = navController.previousBackStackEntry
                        ?.savedStateHandle?.get<MainData>("illness")
                    illness?.let {
                        InfoScreen(illness = it, navController)
                    }
                }

                composable("homeScreen") {
                    HomeScreen(navController, userName = user?.name.toString(), categoryId = 1)
                }
                composable("secondHomeScreen") {
                    SecondHomeScreen(
                        navController,

                        categoryId = 1
                    )
                }
                composable("reminderScreen") {
                    ReminderScreen(navController)
                }
                composable("pillScreen") {
                    PillScreen(navController)
                }
                composable("pillAdd") {
                    PillAddScreen(navController)
                }
                composable("pillInfo") { backStackEntry ->
                    val pillInfo = navController.previousBackStackEntry
                        ?.savedStateHandle?.get<ReminderData>("pillInfo")
                    pillInfo?.let {
                        PillInfoScreen(data = it, navController)
                    }
                }
                composable("pillEdit") { backStackEntry ->
                    val pillEdit= navController.previousBackStackEntry
                        ?.savedStateHandle?.get<ReminderData>("pillEdit")
                    pillEdit?.let {
                        PillEditScreen(pills = it, navController)
                    }
                    if (pillEdit != null) {
                        PillEditScreen(pills = pillEdit,navController)
                    }
                }

            }
        }
    }
}
