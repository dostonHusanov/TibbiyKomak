package com.doston.tibbiykomak.reminder

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun ReminderScreen(navController: NavController,viewModel:ThemeViewModel) {
    val context = LocalContext.current
    val allPills = remember { getAllPills(context) }
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    val today = dateFormat.format(Date())
    val dateRange = remember { generateNext7Days() }

    var selectedDate by remember { mutableStateOf(today) }

    val selectedPills = remember(selectedDate) {
        allPills.filter { it.date.contains(selectedDate) }
    }
    val isDarkTheme by viewModel.themeDark.collectAsState()
    val mainColor = if (isDarkTheme) MainColor else DMainColor
    val textColor = if (isDarkTheme) TextColor else DTextColor
    val textColor2 = if (isDarkTheme) TextColor2 else DTextColor2
    val regColor = if (isDarkTheme) RegColor else DRegColor
    val aColor = if (isDarkTheme) AColor else DAColor


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(mainColor)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                item {
                    val formattedDate = try {
                        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        val outputFormat = SimpleDateFormat("d-MMMM, yyyy", Locale("uz"))
                        val date = inputFormat.parse(selectedDate)
                        outputFormat.format(date ?: Date())
                    } catch (e: Exception) {
                        selectedDate
                    }

                        Text(
                            text = formattedDate,
                            color = textColor2,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(horizontal = 16.dp),
                            fontWeight = FontWeight.Bold
                        )

                }
                item {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        items(dateRange.size) { index ->
                            val date = dateRange[index]
                            val isSelected = date == selectedDate

                            DateItem(
                                date = date,
                                isSelected = isSelected,
                                onClick = { selectedDate = date }
                            )
                        }
                    }
                }
                items(selectedPills.size) { index ->
                    val pill = selectedPills[index]
                    pill.times.forEach { time ->
                        ReminderItem(
                            time = time,
                            name = pill.name,
                            desc = pill.desc,
                            date = pill.date,
                            today = selectedDate,viewModel
                        )
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = { navController.navigate("pillScreen") },
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




@Composable
fun ReminderItem(time: String, name: String, desc: String, date: List<String>, today: String,viewModel: ThemeViewModel) {
    val currentIndex = date.indexOf(today) + 1
    val isDarkTheme by viewModel.themeDark.collectAsState()
    val mainColor = if (isDarkTheme) MainColor else DMainColor
    val textColor = if (isDarkTheme) TextColor else DTextColor
    val textColor2 = if (isDarkTheme) TextColor2 else DTextColor2
    val regColor = if (isDarkTheme) RegColor else DRegColor
    val aColor = if (isDarkTheme) AColor else DAColor

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = textColor)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = textColor),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = time,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp), color = mainColor
                )

                Text(
                    text = if (currentIndex > 0) "$currentIndex/${date.size} Kun" else "-/${date.size} Kun",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(8.dp), color = mainColor
                )

                Text(stringResource(R.string.istemol_qilindi), fontSize = 14.sp, modifier = Modifier.padding(8.dp), color = mainColor)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = regColor)
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.pill),
                    contentDescription = "",
                    modifier = Modifier
                        .size(55.dp)
                        .padding(6.dp), tint = textColor
                )

                Column(modifier = Modifier.padding(start = 8.dp)) {
                    Text(name, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = textColor)
                    Text(desc, fontSize = 12.sp, maxLines = 1, color = textColor)
                }
            }
        }
    }
}
@Composable
fun DateItem(date: String, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) TextColor else Color.White
    val textColor = if (isSelected) Color.White else TextColor

    Column(
        modifier = Modifier
            .background(backgroundColor, RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val day = date.takeLast(2)
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dateObj = sdf.parse(date)
        val weekday = SimpleDateFormat("EEE", Locale.getDefault()).format(dateObj ?: Date())

        Text(weekday, fontSize = 14.sp, color = textColor)
        Text(day, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = textColor)
    }
}
fun getAllPills(context: Context): List<ReminderData> {
    val db = UserDatabaseHelper(context)
    return db.getAllPills()
}
fun generateNext7Days(): List<String> {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val calendar = Calendar.getInstance()

    return List(31) { offset ->
        calendar.time = Date()
        calendar.add(Calendar.DAY_OF_YEAR, offset)
        format.format(calendar.time)
    }
}




