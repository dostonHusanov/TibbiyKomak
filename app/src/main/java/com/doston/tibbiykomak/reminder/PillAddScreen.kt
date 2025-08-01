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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
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
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.YearMonth
import java.util.*

@Composable
fun PillAddScreen(navController: NavController,viewModel: ThemeViewModel) {
    val context = LocalContext.current
    val dbHelper = remember { UserDatabaseHelper(context) }
    val name = remember { mutableStateOf("") }
    val desc = remember { mutableStateOf("") }
    val day = remember { mutableStateOf("") }
    val timesPerDay = remember { mutableStateOf("") }
    val selectedDates = remember { mutableStateListOf<LocalDate>() }
    val showDatePicker = remember { mutableStateOf(false) }
    val timeStates = remember { List(6) { mutableStateOf("") } }
    val isDarkTheme by viewModel.themeDark.collectAsState()
    val mainColor = if (isDarkTheme) MainColor else DMainColor
    val textColor = if (isDarkTheme) TextColor else DTextColor
    val textColor2 = if (isDarkTheme) TextColor2 else DTextColor2
    val regColor = if (isDarkTheme) RegColor else DRegColor
    val aColor = if (isDarkTheme) AColor else DAColor
    Scaffold(containerColor = mainColor) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item {
                Text(
                    text = stringResource(R.string.dori_qo_shish),
                    color = textColor,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )
            }

            item {
                CustomTextField(stringResource(R.string.dori_nomi), name.value ,{ name.value = it }, viewModel = viewModel)
            }
            item {
                CustomTextField(stringResource(R.string.dori_xaqida_qisqa_malumot), desc.value, { desc.value = it }, viewModel = viewModel)
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .background(
                            shape = RoundedCornerShape(10.dp), color = regColor
                        )
                        .clickable { showDatePicker.value = true },
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = if (selectedDates.isEmpty()) stringResource(R.string.sanalarni_tanlang) else
                            selectedDates.joinToString { it.toString() },
                        color = textColor,
                        modifier = Modifier.padding(12.dp)
                    )
                }
                if (showDatePicker.value) {
                    MultipleDatePickerDialog(selectedDates,viewModel) {
                        showDatePicker.value = false
                    }
                }
            }



            item {
                CustomTextField(
                    stringResource(R.string._1_kunda_nechchi_marta_ichiladi),
                    timesPerDay.value,
                    { timesPerDay.value = it },
                    KeyboardType.Number,viewModel
                )
            }

            val count = timesPerDay.value.toIntOrNull() ?: 0
            if (count in 1..6) {
                items(count) { index ->
                    TimePickerField(label = stringResource(R.string.vaqtni_tanlang), timeState = timeStates[index], viewModel = viewModel)
                }
            } else if (count > 6) {
                item {
                    Text(
                        stringResource(R.string._1_kunda_6_mahaldan_ko_p_dori_icha_olmaysiz),
                        color = textColor,
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
                        .background(shape = RoundedCornerShape(10.dp), color = textColor)
                        .clickable {
                            val selectedTimes = timeStates
                                .take(count)
                                .map { it.value }
                                .filter { it.isNotBlank() }

                            if (name.value.isBlank() || desc.value.isBlank() || selectedDates.isEmpty() || timesPerDay.value.isBlank() || selectedTimes.isEmpty()) {
                                Toast
                                    .makeText(
                                        context,
                                        context.getString(R.string.bo_sh_maydonlarni_to_ldiring),
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
                                            context.getString(R.string.iltimos_dori_eslatmalar_uchun_ruxsat_bering),
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
                        text = stringResource(R.string.saqlash),
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


@Composable
fun MultipleDatePickerDialog(
    selectedDates: SnapshotStateList<LocalDate>,viewModel: ThemeViewModel,
    onDismiss: () -> Unit
) {
    val today = remember { LocalDate.now() }
    val currentMonth = remember { mutableStateOf(YearMonth.now()) }
    val isDarkTheme by viewModel.themeDark.collectAsState()
    val mainColor = if (isDarkTheme) MainColor else DMainColor
    val textColor = if (isDarkTheme) TextColor else DTextColor
    val textColor2 = if (isDarkTheme) TextColor2 else DTextColor2
    val regColor = if (isDarkTheme) RegColor else DRegColor
    val aColor = if (isDarkTheme) AColor else DAColor
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("OK", color = textColor)
            }
        },
        title = { Text(stringResource(R.string.sanalarni_tanlang), color = textColor) },
        containerColor = mainColor,
        text = {
            Column(modifier=Modifier.background(mainColor)) {
                Text(
                    "${
                        currentMonth.value.month.name.lowercase()
                            .replaceFirstChar { it.uppercase() }
                    } ${currentMonth.value.year}",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp), color = textColor
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(7),
                    modifier = Modifier.height(270.dp)
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
                            Text(text = "${day + 1}", color = textColor)
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
fun TimePickerField(label: String, timeState: MutableState<String>,viewModel: ThemeViewModel) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)
    val isDarkTheme by viewModel.themeDark.collectAsState()
    val mainColor = if (isDarkTheme) MainColor else DMainColor
    val textColor = if (isDarkTheme) TextColor else DTextColor
    val textColor2 = if (isDarkTheme) TextColor2 else DTextColor2
    val regColor = if (isDarkTheme) RegColor else DRegColor
    val aColor = if (isDarkTheme) AColor else DAColor
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
        label = { Text(label, color = textColor) },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Timer,
                contentDescription = "timer",
                tint = mainColor,
                modifier = Modifier.clickable { dialog.show() }
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 8.dp)
            .clickable { dialog.show() },
        shape = RoundedCornerShape(10),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = textColor,
            unfocusedContainerColor = regColor,
            disabledContainerColor = regColor,
            focusedIndicatorColor = textColor,
            unfocusedIndicatorColor = textColor2,
            cursorColor =mainColor,
            focusedTextColor = mainColor,
            unfocusedTextColor = textColor
        )
    )
}


@Composable
fun CustomTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,viewModel: ThemeViewModel
) {
    val isDarkTheme by viewModel.themeDark.collectAsState()
    val mainColor = if (isDarkTheme) MainColor else DMainColor
    val textColor = if (isDarkTheme) TextColor else DTextColor
    val textColor2 = if (isDarkTheme) TextColor2 else DTextColor2
    val regColor = if (isDarkTheme) RegColor else DRegColor
    val aColor = if (isDarkTheme) AColor else DAColor
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = textColor) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 10.dp),
        shape = RoundedCornerShape(10),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = textColor,
            unfocusedContainerColor = regColor,
            disabledContainerColor = regColor,
            focusedIndicatorColor = textColor,
            unfocusedIndicatorColor =textColor2,
            cursorColor =mainColor,
            focusedTextColor =mainColor,
            unfocusedTextColor = textColor
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}



