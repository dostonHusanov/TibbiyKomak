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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.doston.tibbiykomak.data.ReminderData
import com.doston.tibbiykomak.database.UserDatabaseHelper
import com.doston.tibbiykomak.ui.theme.MainColor
import com.doston.tibbiykomak.ui.theme.TextColor
import com.doston.tibbiykomak.ui.theme.TibbiyKomakTheme

@Composable
fun PillScreen(navController: NavController) {
    val context = LocalContext.current
    val db = remember { UserDatabaseHelper(context) }
    val pills = remember { mutableStateOf(emptyList<ReminderData>()) }
    Scaffold(modifier = Modifier.background(MainColor)) { innerPadding ->


        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MainColor)
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
                            }
                        )
                    }
                }
            }
            FloatingActionButton(
                onClick = { navController.navigate("pillAdd") },
                containerColor = TextColor,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = MainColor
                )
            }
        }
    }
}

@Composable
fun PillItem(pill: ReminderData,
              onClick: () -> Unit = {},
              onEdit: (ReminderData) -> Unit,
              onDelete: (ReminderData) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(TextColor, RoundedCornerShape(5.dp))
    ) {
        Row(
            modifier = Modifier.clickable { onClick() }
                .fillMaxWidth()
                .background(TextColor),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .background(TextColor)
                    .padding(10.dp)
            ) {
                Text(
                    text = pill.name,
                    fontSize = 20.sp,
                    color = MainColor,
                    fontWeight = FontWeight.SemiBold,modifier = Modifier.padding(vertical = 2.dp, horizontal = 2.dp)
                )

                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "${pill.date.count()} kun", fontSize = 16.sp, color = MainColor,modifier = Modifier.padding(vertical = 2.dp, horizontal = 2.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Har kuni ${pill.times.count()} marta istemol qilinadi",
                        fontSize = 16.sp,
                        color = MainColor,
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
                    tint = MainColor
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

@Preview(showBackground = true)
@Composable
fun PillScreenPreview() {
    TibbiyKomakTheme {
        PillScreen(rememberNavController())
    }
}

