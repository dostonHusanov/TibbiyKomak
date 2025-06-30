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
fun PillAddScreen(navController: NavController) {
    val context = LocalContext.current
    val dbHelper = remember { UserDatabaseHelper(context) }
    val name = remember { mutableStateOf("") }
    val desc = remember { mutableStateOf("") }
    val day = remember { mutableStateOf("") }
    val timesPerDay = remember { mutableStateOf("") }
    val error = remember { mutableStateOf("") }

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
                        .background(
                            shape = RoundedCornerShape(10.dp), color = TextColor
                        )
                        .clickable {
                            val selectedTimes = timeStates
                                .take(count)
                                .map { it.value }
                                .filter { it.isNotBlank() }
                            if (name.value.isBlank() || desc.value.isBlank() || day.value.isBlank() || timesPerDay.value.isBlank() || selectedTimes.isEmpty()) {
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
                                    day = day.value,
                                    times = selectedTimes
                                )
                                dbHelper.insertPill(data)
                                navController.popBackStack()
                            }
                        }, contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Saqlash",
                        fontSize = 18.sp,
                        color = MainColor,
                        fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp)
                    )

                }
            }
        }
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
