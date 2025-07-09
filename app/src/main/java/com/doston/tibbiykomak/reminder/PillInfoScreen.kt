package com.doston.tibbiykomak.reminder

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.doston.tibbiykomak.data.ReminderData
import com.doston.tibbiykomak.ui.theme.MainColor
import com.doston.tibbiykomak.ui.theme.TextColor
import com.doston.tibbiykomak.ui.theme.TibbiyKomakTheme

@Composable
fun PillInfoScreen(data: ReminderData, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainColor)
            .padding(WindowInsets.statusBars.asPaddingValues())
            .padding(horizontal = 16.dp)
            .padding(WindowInsets.systemBars.asPaddingValues()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Dori Tafsilotlari",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = TextColor,

                )
            Spacer(modifier = Modifier.height(14.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(4.dp, RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = TextColor)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    PillDetailRow(
                        icon = Icons.Default.MedicalServices,
                        label = "Dori nomi",
                        value = data.name
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    PillDetailRow(
                        icon = Icons.Default.Info,
                        label = "Dori haqida",
                        value = data.desc
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    PillDetailRow(
                        icon = Icons.Default.CalendarToday,
                        label = "Kunlar soni",
                        value = "${data.day} kun"
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    PillDetailRow(
                        icon = Icons.Default.Schedule,
                        label = "Vaqtlari",
                        value = data.times.joinToString(", ")
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = TextColor,
                    RoundedCornerShape(10.dp)
                )
                .clickable { navController.popBackStack() },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Oraga qytish",
                color = MainColor,
                fontSize = 16.sp,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@Composable
fun PillDetailRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = MainColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = label,
                fontSize = 14.sp,
                color = MainColor
            )
            Text(
                text = value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MainColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PillInfoScreenPreview() {
    TibbiyKomakTheme {
        val data = ReminderData(
            name = "Paracetamol",
            desc = "Og'riq qoldiruvchi va isitma tushiruvchi dori.",
            day = "7",
            times = listOf("07:00", "13:00", "20:30")
        )
        PillInfoScreen(data, rememberNavController())
    }
}
