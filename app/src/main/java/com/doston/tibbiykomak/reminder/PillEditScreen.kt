package com.doston.tibbiykomak.reminder


import android.app.TimePickerDialog
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import java.util.Calendar

@Composable
fun PillEditScreen(pills: ReminderData, navController: NavController) {
    val context = LocalContext.current
    val dbHelper = remember { UserDatabaseHelper(context) }
    val name = remember { mutableStateOf(pills.name) }
    val desc = remember { mutableStateOf(pills.desc) }
    val day = remember { mutableStateOf(pills.day) }

    // State for the NUMBER of times per day (e.g., "3")
    // Initialize with the size of the existing times list
    val timesPerDayCountString = remember { mutableStateOf(pills.times.size.toString()) }

    // State for the actual selected time strings (e.g., "08:00", "14:00")
    // Initialize with existing times, padding with empty strings if necessary
    val timeStates = remember {
        List(6) { index ->
            mutableStateOf(pills.times.getOrNull(index) ?: "")
        }
    }

    val error = remember { mutableStateOf("") } // You might want to use this for validation errors

    Scaffold(containerColor = MainColor) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item {
                Text(
                    text = "Dori o'zgartirish", // Changed text to reflect editing
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
                CustomTextField(
                    "Nechchi kun",
                    day.value,
                    { day.value = it },
                    KeyboardType.Number
                )
            }
            item {
                CustomTextField(
                    "1 kunda nechchi marta ichiladi",
                    timesPerDayCountString.value, // Use the new state
                    { newValue ->
                        // Basic validation: allow only digits and ensure it's within a reasonable range if needed
                        if (newValue.all { it.isDigit() }) {
                            timesPerDayCountString.value = newValue
                        } else if (newValue.isEmpty()) {
                            timesPerDayCountString.value = ""
                        }
                    },
                    KeyboardType.Number
                )
            }

            // Safely parse the count
            val count = timesPerDayCountString.value.toIntOrNull() ?: 0

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
            } else if (timesPerDayCountString.value.isNotEmpty() && count == 0) {
                // Handle case where user enters "0" or invalid non-empty string that parses to 0
                item {
                    Text(
                        "Kamida 1 mahal dori ichishingiz kerak.",
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
                        .background(
                            shape = RoundedCornerShape(10.dp), color = TextColor
                        )
                        .clickable {
                            val currentCount = timesPerDayCountString.value.toIntOrNull() ?: 0
                            val selectedTimes = timeStates
                                .take(currentCount) // Use currentCount
                                .map { it.value }
                                .filter { it.isNotBlank() }

                            if (name.value.isBlank() ||
                                desc.value.isBlank() ||
                                day.value.isBlank() ||
                                timesPerDayCountString.value.isBlank() ||
                                currentCount == 0
                            ) { // Check if count is zero
                                Toast
                                    .makeText(
                                        context,
                                        "Bo'sh maydonlarni to'ldiring!",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            } else if (selectedTimes.size != currentCount) {
                                Toast
                                    .makeText(
                                        context,
                                        "Iltimos, barcha vaqtlarni tanlang.",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            } else {
                                val data = ReminderData(
                                    id = pills.id,
                                    name = name.value,
                                    desc = desc.value,
                                    day = day.value,
                                    times = selectedTimes
                                )

                                dbHelper.editPill(data)

                                if (context.hasExactAlarmPermission()) {
                                    AlarmScheduler.cancelAlarmsForPill(context, pills) // cancel old
                                    AlarmScheduler.scheduleAlarmsForPill(context, data) // schedule new
                                } else {
                                    context.requestExactAlarmPermission()
                                    Toast.makeText(
                                        context,
                                        "Iltimos, dori eslatmalar uchun ruxsat bering",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }

                                navController.popBackStack()



                            }
                        }, contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "O'zgartirish",
                        fontSize = 18.sp,
                        color = MainColor,
                        fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PillEditScreenPreview() {
    TibbiyKomakTheme {
        val data = ReminderData(
            name = "Paracetamol",
            desc = "Og'riq qoldiruvchi va isitma tushiruvchi dori.",
            day = "7",
            times = listOf("07:00", "13:00", "20:30")
        )
        PillEditScreen(data, rememberNavController())
    }
}
