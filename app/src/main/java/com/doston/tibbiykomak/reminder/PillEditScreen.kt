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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import java.util.Calendar

import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun PillEditScreen(pills: ReminderData, navController: NavController,viewModel: ThemeViewModel) {
    val context = LocalContext.current
    val dbHelper = remember { UserDatabaseHelper(context) }
    val isDarkTheme by viewModel.themeDark.collectAsState()
    val mainColor = if (isDarkTheme) MainColor else DMainColor
    val textColor = if (isDarkTheme) TextColor else DTextColor
    val textColor2 = if (isDarkTheme) TextColor2 else DTextColor2
    val regColor = if (isDarkTheme) RegColor else DRegColor
    val aColor = if (isDarkTheme) AColor else DAColor
    val name = remember { mutableStateOf(pills.name) }
    val desc = remember { mutableStateOf(pills.desc) }

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val selectedDates = remember {
        mutableStateListOf<LocalDate>().apply {
            addAll(pills.date.mapNotNull { runCatching { LocalDate.parse(it) }.getOrNull() })
        }
    }

    val timesPerDayCountString = remember { mutableStateOf(pills.times.size.toString()) }
    val showDatePicker = remember { mutableStateOf(false) }

    val timeStates = remember {
        List(6) { index ->
            mutableStateOf(pills.times.getOrNull(index) ?: "")
        }
    }

    Scaffold(containerColor = mainColor) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item {
                Text(
                    text = "Dori o'zgartirish",
                    color = textColor,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )
            }

            item {
                CustomTextField("Dori nomi", name.value, { name.value = it }, viewModel = viewModel)
            }

            item {
                CustomTextField("Dori xaqida qisqa malumot", desc.value, { desc.value = it },viewModel=viewModel)
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 8.dp)
                        .background(regColor, RoundedCornerShape(10.dp))
                        .clickable { showDatePicker.value = true }
                        .padding(16.dp)
                ) {
                    Text(
                        text = if (selectedDates.isEmpty()) "Sana(lar)ni tanlang"
                        else selectedDates.joinToString { it.format(formatter) },
                        color = textColor
                    )
                }
                if (showDatePicker.value) {
                    MultipleDatePickerDialog(
                        selectedDates = selectedDates, viewModel = viewModel
                    ){
                        showDatePicker.value = false
                    }
                }
            }



            item {
                CustomTextField(
                    "1 kunda nechchi marta ichiladi",
                    timesPerDayCountString.value,
                    { newValue ->
                        if (newValue.all { it.isDigit() }) {
                            timesPerDayCountString.value = newValue
                        } else if (newValue.isEmpty()) {
                            timesPerDayCountString.value = ""
                        }
                    },
                    KeyboardType.Number,viewModel
                )
            }

            val count = timesPerDayCountString.value.toIntOrNull() ?: 0

            if (count in 1..6) {
                items(count) { index ->
                    TimePickerField(label = "Vaqtni tanlang", timeState = timeStates[index],viewModel)
                }
            } else if (count > 6) {
                item {
                    Text(
                        "1 kunda 6 mahaldan ko'p dori icha olmaysiz!",
                        color = textColor2,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            } else if (timesPerDayCountString.value.isNotEmpty() && count == 0) {
                item {
                    Text(
                        "Kamida 1 mahal dori ichishingiz kerak.",
                        color = textColor2,
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
                            shape = RoundedCornerShape(10.dp), color = textColor
                        )
                        .clickable {
                            val selectedTimes = timeStates
                                .take(count)
                                .map { it.value }
                                .filter { it.isNotBlank() }

                            if (
                                name.value.isBlank() || desc.value.isBlank()
                                || selectedDates.isEmpty() || timesPerDayCountString.value.isBlank()
                                || selectedTimes.size != count
                            ) {
                                Toast.makeText(
                                    context,
                                    "Iltimos, barcha maydonlarni to'ldiring!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                val data = ReminderData(
                                    id = pills.id,
                                    name = name.value,
                                    desc = desc.value,
                                    date = selectedDates.map { it.format(formatter) },
                                    times = selectedTimes
                                )

                                dbHelper.editPill(data)

                                if (context.hasExactAlarmPermission()) {
                                    AlarmScheduler.cancelAlarmsForPill(context, pills)
                                    AlarmScheduler.scheduleAlarmsForPill(context, data)
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
                        color = mainColor,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }
    }
}
