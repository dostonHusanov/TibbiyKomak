package com.doston.tibbiykomak.navigation

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Help
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
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.doston.tibbiykomak.R
import com.doston.tibbiykomak.SupportScreen
import com.doston.tibbiykomak.data.MainData
import com.doston.tibbiykomak.data.ReminderData
import com.doston.tibbiykomak.data.User
import com.doston.tibbiykomak.database.UserDatabaseHelper
import com.doston.tibbiykomak.home.AboutScreen
import com.doston.tibbiykomak.home.ContactScreen
import com.doston.tibbiykomak.home.HomeScreen
import com.doston.tibbiykomak.home.InfoScreen
import com.doston.tibbiykomak.reminder.PillAddScreen
import com.doston.tibbiykomak.reminder.PillEditScreen
import com.doston.tibbiykomak.reminder.PillInfoScreen
import com.doston.tibbiykomak.reminder.PillScreen
import com.doston.tibbiykomak.reminder.ReminderScreen
import com.doston.tibbiykomak.ui.theme.AColor
import com.doston.tibbiykomak.ui.theme.DAColor
import com.doston.tibbiykomak.ui.theme.DMainColor
import com.doston.tibbiykomak.ui.theme.DRegColor
import com.doston.tibbiykomak.ui.theme.DTextColor
import com.doston.tibbiykomak.ui.theme.DTextColor2
import com.doston.tibbiykomak.ui.theme.MainColor
import com.doston.tibbiykomak.ui.theme.RegColor
import com.doston.tibbiykomak.ui.theme.TextColor
import com.doston.tibbiykomak.ui.theme.TextColor2
import com.doston.tibbiykomak.ui.theme.ThemeViewModel
import kotlinx.coroutines.launch

data class BottomNavItem(
    val route: String,
    val title: String,
    val icon: @Composable () -> Unit
)

@SuppressLint("StateFlowValueCalledInComposition", "SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondaryNav(viewModel: ThemeViewModel) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val context = LocalContext.current
    val dbHelper = remember { UserDatabaseHelper(context) }
    var user by remember { mutableStateOf<User?>(null) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val isDarkTheme by viewModel.themeDark.collectAsState()
    val mainColor = if (isDarkTheme) MainColor else DMainColor
    val textColor = if (isDarkTheme) TextColor else DTextColor
    val textColor2 = if (isDarkTheme) TextColor2 else DTextColor2
    val regColor = if (isDarkTheme) RegColor else DRegColor
    val aColor = if (isDarkTheme) AColor else DAColor

    LaunchedEffect(Unit) {
        user = dbHelper.getUser()
    }
    val bottomNavItems = listOf(
        BottomNavItem(
            route = "homeScreen",
            title = stringResource(R.string.bosh_sahifa),
            icon = {
                Icon(
                    painterResource(R.drawable.home),
                    contentDescription = "Home",
                    tint = textColor
                )
            }
        ),
        BottomNavItem(
            route = "reminderScreen",
            title = stringResource(R.string.eslatma),
            icon = {
                Icon(
                    painterResource(R.drawable.bell),
                    contentDescription = "Reminder",
                    tint = textColor
                )
            }
        )
    )



    ModalNavigationDrawer(gesturesEnabled = currentRoute != "infoScreen",
        drawerState = drawerState, modifier = Modifier,
        drawerContent = {
            ModalDrawerSheet(drawerContainerColor = MainColor) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(mainColor)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    Column(modifier = Modifier.fillMaxWidth()) {
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
                                colors = CardDefaults.cardColors(textColor2)
                            ) {
                                Card(
                                    shape = CircleShape,
                                    modifier = Modifier.padding(3.dp),
                                    colors = CardDefaults.cardColors(mainColor)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Person,
                                        contentDescription = "Profile image",
                                        modifier = Modifier
                                            .size(60.dp)
                                            .padding(6.dp), tint = textColor
                                    )
                                }
                            }

                            Icon(
                                painter = painterResource(
                                    if (isDarkTheme) R.drawable.night_mode else R.drawable.light_mode
                                ), tint = textColor,
                                contentDescription = "Toggle Theme",
                                modifier = Modifier
                                    .size(34.dp)
                                    .clip(CircleShape)
                                    .background(mainColor)
                                    .shadow(0.dp, shape = CircleShape)
                                    .clickable {
                                        viewModel.setTheme(!isDarkTheme)
                                    }
                            )
                        }


                        Text(
                            "${user?.name} ${user?.surname}",
                            modifier = Modifier.padding(horizontal = 16.dp),
                            fontSize = 18.sp, fontWeight = FontWeight.Bold, color = textColor
                        )
                        Spacer(Modifier.height(2.dp))
                        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                            Text(
                                user?.age.toString(),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = textColor
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                stringResource(R.string.yosh),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = textColor
                            )
                        }
                        Spacer(Modifier.height(2.dp))
                        Text(
                            "${user?.phoneNumber}",
                            modifier = Modifier.padding(horizontal = 16.dp),
                            fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = textColor
                        )

                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 10.dp),
                            color = textColor
                        )

                        NavigationDrawerItem(
                            label = { Text(stringResource(R.string.qollab_quvvatlash)) },
                            selected = false,
                            icon = { Icon(Icons.Outlined.Help, contentDescription = null) },
                            onClick = {
                                scope.launch { drawerState.close() }
                                navController.navigate("support")

                            },
                            colors = NavigationDrawerItemDefaults.colors(
                                unselectedContainerColor = textColor,
                                unselectedTextColor = mainColor,
                                unselectedBadgeColor = mainColor,
                                unselectedIconColor = mainColor
                            ),
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .background(shape = RoundedCornerShape(10.dp), color = textColor)
                        )
                        Spacer(Modifier.height(10.dp))
                        NavigationDrawerItem(
                            label = { Text(stringResource(R.string.ilova_haqida)) },
                            selected = false,
                            icon = { Icon(Icons.Outlined.Info, contentDescription = null) },
                            onClick = {
                                scope.launch { drawerState.close() }
                                navController.navigate("aboutScreen")

                            },
                            colors = NavigationDrawerItemDefaults.colors(
                                unselectedContainerColor = textColor,
                                unselectedTextColor = mainColor,
                                unselectedBadgeColor = mainColor,
                                unselectedIconColor = mainColor
                            ),
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .background(shape = RoundedCornerShape(10.dp), color = textColor)
                        )
                        Spacer(Modifier.height(10.dp))

                    }
                    Spacer(Modifier.height(10.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp, horizontal = 24.dp)
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.telegram),
                            contentDescription = "Telegram",
                            modifier = Modifier
                                .size(32.dp)
                                .clickable {
                                    val telegramUrl = "https://t.me/Husanov_Doston"
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(telegramUrl))
                                    context.startActivity(intent)
                                },
                            tint = Color.Unspecified

                        )

                        Icon(
                            painter = painterResource(id = R.drawable.instagram),
                            contentDescription = "Instagram",
                            modifier = Modifier
                                .size(32.dp)
                                .clickable {
                                    val instagramUrl = "https://www.instagram.com/tibbiykomak/"
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl))
                                    context.startActivity(intent)
                                },
                            tint = Color.Unspecified

                        )


                        Icon(
                            painter = painterResource(id = R.drawable.youtube),
                            contentDescription = "YouTube",
                            modifier = Modifier
                                .size(32.dp)
                                .clickable {
                                    val youtubeUrl = "https://www.youtube.com/@doston_husanov"
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl))
                                    context.startActivity(intent)
                                },
                            tint = Color.Unspecified
                        )

                        Icon(
                            painter = painterResource(R.drawable.phone),
                            contentDescription = "Phone",
                            modifier = Modifier
                                .size(32.dp)
                                .clickable {
                                    val intent = Intent(Intent.ACTION_DIAL)
                                    intent.data = Uri.parse("tel:+998918032662")
                                    context.startActivity(intent)
                                },
                            tint = textColor

                        )
                    }


                }
            }
        }
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val isBottomBarVisible =
            currentRoute in listOf("homeScreen", "reminderScreen")

        if (isBottomBarVisible) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "Tibbiy Ko'mak",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = textColor2
                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = mainColor,
                            titleContentColor = textColor
                        ),
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    if (drawerState.isClosed) drawerState.open() else drawerState.close()
                                }
                            }) {
                                Icon(
                                    Icons.Default.Menu,
                                    contentDescription = "Menu",
                                    tint = textColor2
                                )
                            }
                        }
                    )
                },
                bottomBar = {
                    NavigationBar(
                        containerColor = mainColor,
                        modifier = Modifier.border(0.3.dp, textColor2)
                    ) {
                        bottomNavItems.forEach { item ->
                            NavigationBarItem(
                                modifier = Modifier.size(20.dp),
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
                                icon = { item.icon() },
                                label = {
                                    Text(
                                        text = item.title,
                                        fontSize = 10.sp
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = textColor2,
                                    unselectedIconColor = textColor2,
                                    selectedTextColor = textColor2,
                                    unselectedTextColor = textColor2,
                                    indicatorColor = regColor
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
                        HomeScreen(navController, categoryId = 1, viewModel = viewModel)
                    }

                    composable("reminderScreen") {
                        ReminderScreen(navController, viewModel)
                    }
                    composable("pillScreen") {
                        PillScreen(navController, viewModel)
                    }
                    composable("pillAdd") {
                        PillAddScreen(navController, viewModel)
                    }

                    composable("aboutScreen") { AboutScreen(navController, viewModel) }
                    composable("contactScreen") { ContactScreen(navController, viewModel) }
                    composable("infoScreen") { backStackEntry ->
                        val illness = navController.previousBackStackEntry
                            ?.savedStateHandle?.get<MainData>("illness")
                        illness?.let {
                            InfoScreen(illness = it, navController, viewModel)
                        }
                    }
                    composable("pillInfo") { backStackEntry ->
                        val pillInfo = navController.previousBackStackEntry
                            ?.savedStateHandle?.get<ReminderData>("pillInfo")
                        pillInfo?.let {
                            PillInfoScreen(data = it, navController, viewModel)
                        }
                    }
                    composable("pillEdit") { backStackEntry ->
                        val pillEdit = navController.previousBackStackEntry
                            ?.savedStateHandle?.get<ReminderData>("pillEdit")
                        pillEdit?.let {
                            PillEditScreen(pills = it, navController, viewModel)
                        }
                        if (pillEdit != null) {
                            PillEditScreen(pills = pillEdit, navController, viewModel)
                        }
                    }
                    composable("support") {
                        SupportScreen(navController,viewModel)
                    }
                }
            }
        } else {

            NavHost(navController = navController, startDestination = "homeScreen") {
                composable("aboutScreen") { AboutScreen(navController, viewModel) }
                composable("contactScreen") { ContactScreen(navController, viewModel) }

                composable("infoScreen") { backStackEntry ->
                    val illness = navController.previousBackStackEntry
                        ?.savedStateHandle?.get<MainData>("illness")
                    illness?.let {
                        InfoScreen(illness = it, navController, viewModel)
                    }
                }

                composable("homeScreen") {
                    HomeScreen(navController, categoryId = 1, viewModel = viewModel)
                }

                composable("reminderScreen") {
                    ReminderScreen(navController, viewModel)
                }
                composable("pillScreen") {
                    PillScreen(navController, viewModel)
                }
                composable("pillAdd") {
                    PillAddScreen(navController, viewModel)
                }
                composable("pillInfo") { backStackEntry ->
                    val pillInfo = navController.previousBackStackEntry
                        ?.savedStateHandle?.get<ReminderData>("pillInfo")
                    pillInfo?.let {
                        PillInfoScreen(data = it, navController, viewModel)
                    }
                }
                composable("pillEdit") { backStackEntry ->
                    val pillEdit = navController.previousBackStackEntry
                        ?.savedStateHandle?.get<ReminderData>("pillEdit")
                    pillEdit?.let {
                        PillEditScreen(pills = it, navController, viewModel)
                    }
                    if (pillEdit != null) {
                        PillEditScreen(pills = pillEdit, navController, viewModel)
                    }
                }
                composable("support") {
                    SupportScreen(navController,viewModel)
                }
            }
        }
    }
}

