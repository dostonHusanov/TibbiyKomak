package com.doston.tibbiykomak.reminder

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.doston.tibbiykomak.R
import com.doston.tibbiykomak.data.ReminderData
import com.doston.tibbiykomak.database.UserDatabaseHelper
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
import com.doston.tibbiykomak.ui.theme.TibbiyKomakTheme

@Composable
fun PillScreen(navController: NavController,viewModel: ThemeViewModel) {
    val context = LocalContext.current
    val db = remember { UserDatabaseHelper(context) }
    val pills = remember { mutableStateOf(emptyList<ReminderData>()) }
    val isDarkTheme by viewModel.themeDark.collectAsState()
    val mainColor = if (isDarkTheme) MainColor else DMainColor
    val textColor = if (isDarkTheme) TextColor else DTextColor
    val textColor2 = if (isDarkTheme) TextColor2 else DTextColor2
    val regColor = if (isDarkTheme) RegColor else DRegColor
    val aColor = if (isDarkTheme) AColor else DAColor
    Scaffold(modifier = Modifier.background(MainColor)) { innerPadding ->


        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(mainColor)
                .padding(innerPadding)
        ) {

            LaunchedEffect(Unit) {
                pills.value = db.getAllPills().sortedByDescending { it.id }

            }
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(modifier = Modifier) {
                    items(pills.value) { pill ->
                        PillItem(
                            pill = pill,
                            onClick = {
                                navController.currentBackStackEntry?.savedStateHandle?.set("pillInfo", pill)
                                navController.navigate("pillInfo")
                            },
                            onEdit = {
                                navController.currentBackStackEntry?.savedStateHandle?.set("pillEdit", pill)
                                navController.navigate("pillEdit")
                            },
                            onDelete = {
                                AlarmScheduler.cancelAlarmsForPill(context, pill)
                                db.deletePill(pill.id)
                                pills.value = db.getAllPills().sortedByDescending { it.id }
                            },viewModel
                        )
                    }
                }
            }
            FloatingActionButton(
                onClick = { navController.navigate("pillAdd") },
                containerColor = textColor,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = mainColor
                )
            }
        }
    }
}

@Composable
fun PillItem(pill: ReminderData,
              onClick: () -> Unit = {},
              onEdit: (ReminderData) -> Unit,
              onDelete: (ReminderData) -> Unit,viewModel: ThemeViewModel) {
    val isDarkTheme by viewModel.themeDark.collectAsState()
    val mainColor = if (isDarkTheme) MainColor else DMainColor
    val textColor = if (isDarkTheme) TextColor else DTextColor
    val textColor2 = if (isDarkTheme) TextColor2 else DTextColor2
    val regColor = if (isDarkTheme) RegColor else DRegColor
    val aColor = if (isDarkTheme) AColor else DAColor
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(textColor, RoundedCornerShape(5.dp))
    ) {
        Row(
            modifier = Modifier
                .clickable { onClick() }
                .fillMaxWidth()
                .background(textColor),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .background(textColor)
                    .padding(10.dp)
            ) {
                Text(
                    text = pill.name,
                    fontSize = 20.sp,
                    color = mainColor,
                    fontWeight = FontWeight.SemiBold,modifier = Modifier.padding(vertical = 2.dp, horizontal = 2.dp)
                )

                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "${pill.date.count()} kun", fontSize = 16.sp, color = mainColor,modifier = Modifier.padding(vertical = 2.dp, horizontal = 2.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(
                            R.string.har_kuni_marta_istemol_qilinadi,
                            pill.times.count()
                        ),
                        fontSize = 16.sp,
                        color = mainColor,
                        modifier = Modifier.padding(vertical = 2.dp, horizontal = 2.dp)
                    )
                }
            }

            val expanded = remember { mutableStateOf(false) }

            Box {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "popup menu",
                    modifier = Modifier
                        .clickable { expanded.value = true }
                        .padding(5.dp),
                    tint = mainColor
                )

                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Edit") },
                        onClick = {
                            expanded.value = false
                            onEdit(pill)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Delete") },
                        onClick = {
                            expanded.value = false
                            onDelete(pill)
                        }
                    )
                }
            }

        }
    }
}


