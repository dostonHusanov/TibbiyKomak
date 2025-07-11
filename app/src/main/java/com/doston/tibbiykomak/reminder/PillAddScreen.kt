package com.doston.tibbiykomak.reminder

import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.doston.tibbiykomak.data.ReminderData
import com.doston.tibbiykomak.database.UserDatabaseHelper
import com.doston.tibbiykomak.ui.theme.MainColor
import com.doston.tibbiykomak.ui.theme.RegColor
import com.doston.tibbiykomak.ui.theme.TextColor
import com.doston.tibbiykomak.ui.theme.TibbiyKomakTheme
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.YearMonth
import java.util.*

@Composable
fun PillAddScreen(navController: NavController) {
    val context = LocalContext.current
    val dbHelper = remember { UserDatabaseHelper(context) }
    val name = remember { mutableStateOf("") }
    val desc = remember { mutableStateOf("") }
    val day = remember { mutableStateOf("") }
    val timesPerDay = remember { mutableStateOf("") }
    val selectedDates = remember { mutableStateListOf<LocalDate>() }
    val showDatePicker = remember { mutableStateOf(false) }
    val timeStates = remember { List(6) { mutableStateOf("") } }

    Scaffold(containerColor = MainColor) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item {
                Text(
                    text = "Dori qo'shish",
                    color = TextColor,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )
            }

            item {
                CustomTextField("Dori nomi", name.value, { name.value = it })
            }
            item {
                CustomTextField("Dori xaqida qisqa malumot", desc.value, { desc.value = it })
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .background(
                            shape = RoundedCornerShape(10.dp), color = RegColor
                        )
                        .clickable { showDatePicker.value = true },
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = if (selectedDates.isEmpty()) "Sanalarni tanlang" else
                            selectedDates.joinToString { it.toString() },
                        color = TextColor,
                        modifier = Modifier.padding(12.dp)
                    )
                }
                if (showDatePicker.value) {
                    MultipleDatePickerDialog(selectedDates) {
                        showDatePicker.value = false
                    }
                }
            }



            item {
                CustomTextField(
                    "1 kunda nechchi marta ichiladi",
                    timesPerDay.value,
                    { timesPerDay.value = it },
                    KeyboardType.Number
                )
            }

            val count = timesPerDay.value.toIntOrNull() ?: 0
            if (count in 1..6) {
                items(count) { index ->
                    TimePickerField(label = "Vaqtni tanlang", timeState = timeStates[index])
                }
            } else if (count > 6) {
                item {
                    Text(
                        "1 kunda 6 mahaldan ko'p dori icha olmaysiz!",
                        color = Color.Red,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                        .background(shape = RoundedCornerShape(10.dp), color = TextColor)
                        .clickable {
                            val selectedTimes = timeStates
                                .take(count)
                                .map { it.value }
                                .filter { it.isNotBlank() }

                            if (name.value.isBlank() || desc.value.isBlank() || selectedDates.isEmpty() || timesPerDay.value.isBlank() || selectedTimes.isEmpty()) {
                                Toast
                                    .makeText(
                                        context,
                                        "Bo'sh maydonlarni to'ldiring!",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            } else {
                                val data = ReminderData(
                                    name = name.value,
                                    desc = desc.value,
                                    date = selectedDates.map { it.toString() },
                                    times = selectedTimes
                                )

                                dbHelper.insertPill(data)
                                val lastPill = dbHelper
                                    .getAllPills()
                                    .maxByOrNull { it.id ?: 0 }

                                if (context.hasExactAlarmPermission()) {
                                    lastPill?.let {
                                        AlarmScheduler.scheduleAlarmsForPill(context, it)
                                    }
                                } else {
                                    context.requestExactAlarmPermission()
                                    Toast
                                        .makeText(
                                            context,
                                            "Iltimos, dori eslatmalar uchun ruxsat bering",
                                            Toast.LENGTH_LONG
                                        )
                                        .show()
                                }

                                navController.popBackStack()
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Saqlash",
                        fontSize = 18.sp,
                        color = MainColor,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun MultipleDatePickerDialog(
    selectedDates: SnapshotStateList<LocalDate>,
    onDismiss: () -> Unit
) {
    val today = remember { LocalDate.now() }
    val currentMonth = remember { mutableStateOf(YearMonth.now()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("OK")
            }
        },
        title = { Text("Sanalarni tanlang") },
        text = {
            Column {
                Text(
                    "${
                        currentMonth.value.month.name.lowercase()
                            .replaceFirstChar { it.uppercase() }
                    } ${currentMonth.value.year}",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(7),
                    modifier = Modifier.height(300.dp)
                ) {
                    val days = currentMonth.value.lengthOfMonth()
                    val startDayOfWeek = currentMonth.value.atDay(1).dayOfWeek.value % 7

                    // Empty cells before start of month
                    items(startDayOfWeek) {
                        Box(modifier = Modifier.size(40.dp))
                    }

                    items(days) { day ->
                        val date = currentMonth.value.atDay(day + 1)
                        val isSelected = selectedDates.contains(date)

                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .padding(4.dp)
                                .background(
                                    color = if (isSelected) Color.Green else Color.Transparent,
                                    shape = MaterialTheme.shapes.small
                                )
                                .clickable {
                                    if (isSelected) {
                                        selectedDates.remove(date)
                                    } else {
                                        selectedDates.add(date)
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "${day + 1}")
                        }
                    }
                }
            }
        }
    )
}

fun Context.hasExactAlarmPermission(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.canScheduleExactAlarms()
    } else true
}

fun Context.requestExactAlarmPermission() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
        intent.data = Uri.parse("package:$packageName")
        startActivity(intent)
    }
}


@Composable
fun TimePickerField(label: String, timeState: MutableState<String>) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    val dialog = remember {
        TimePickerDialog(
            context,
            { _, h: Int, m: Int ->
                timeState.value = String.format("%02d:%02d", h, m)
            },
            hour, minute, true
        )
    }

    OutlinedTextField(
        value = timeState.value,
        onValueChange = {},
        readOnly = true,
        label = { Text(label) },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Timer,
                contentDescription = "timer",
                tint = MainColor,
                modifier = Modifier.clickable { dialog.show() }
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 8.dp)
            .clickable { dialog.show() },
        shape = RoundedCornerShape(10),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = RegColor,
            unfocusedContainerColor = RegColor,
            disabledContainerColor = RegColor,
            focusedIndicatorColor = TextColor,
            unfocusedIndicatorColor = Color.Gray,
            cursorColor = Color.Black,
            focusedTextColor = TextColor,
            unfocusedTextColor = TextColor
        )
    )
}


@Composable
fun CustomTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 10.dp),
        shape = RoundedCornerShape(10),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = RegColor,
            unfocusedContainerColor = RegColor,
            disabledContainerColor = RegColor,
            focusedIndicatorColor = TextColor,
            unfocusedIndicatorColor = Color.Gray,
            cursorColor = Color.Black,
            focusedTextColor = TextColor,
            unfocusedTextColor = TextColor
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}


@Preview(showBackground = true)
@Composable
fun PillAddScreenPreview() {
    TibbiyKomakTheme {
        PillAddScreen(rememberNavController())
    }
}
