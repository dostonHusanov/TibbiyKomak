package com.doston.tibbiykomak.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.doston.tibbiykomak.data.MainData
import com.doston.tibbiykomak.ui.theme.MainColor
import com.doston.tibbiykomak.ui.theme.TextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(illness: MainData, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainColor)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = illness.problem,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = TextColor
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = illness.description,
            fontSize = 16.sp,
            modifier=Modifier.padding(8.dp,0.dp,0.dp,0.dp)
            ,fontWeight = FontWeight.Normal,
            color = TextColor, maxLines = 5
        )

        Spacer(modifier = Modifier.height(12.dp))

        Divider(color = TextColor.copy(alpha = 0.2f), thickness = 1.dp)

        Spacer(modifier = Modifier.height(12.dp))

        SectionTitle(title = "Tavsiya etilgan dori vositalar")
        illness.recommendedPills.forEach {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                colors = CardDefaults.cardColors(containerColor = TextColor),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                Column(modifier = Modifier
                    .padding(12.dp)
                    .background(TextColor)) {
                    Text(
                        text = it.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MainColor
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = it.description, fontSize = 14.sp, color = MainColor)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "🕒 ${it.usage}",
                        fontSize = 13.sp,
                        color = MainColor.copy(alpha = 0.8f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        SectionTitle(title = "Uy sharoitida tavsiyalar")
        illness.homeAdvice.forEach {
            Text(
                text = "• $it",
                fontSize = 15.sp,
                color = TextColor,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        Card(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            colors = CardColors(
                contentColor = MainColor,
                containerColor = TextColor,
                disabledContentColor = MainColor,
                disabledContainerColor = TextColor.copy(alpha = 0.05f)
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tugatish",
                    fontSize = 18.sp,
                    color = MainColor,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = TextColor
    )
    Spacer(modifier = Modifier.height(8.dp))
}
