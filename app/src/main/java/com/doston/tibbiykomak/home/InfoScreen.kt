package com.doston.tibbiykomak.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.doston.tibbiykomak.data.MainData
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(illness: MainData, navController: NavController,viewModel: ThemeViewModel) {
    val isDarkTheme by viewModel.themeDark.collectAsState()
    val mainColor= if (isDarkTheme) MainColor else DMainColor
    val textColor=if (isDarkTheme) TextColor else DTextColor
    val textColor2=if(isDarkTheme) TextColor2 else DTextColor2
    val regColor=if (isDarkTheme) RegColor else DRegColor
    val aColor=if(isDarkTheme) AColor else DAColor

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(mainColor)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = illness.problem,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = illness.description,
            fontSize = 16.sp,
            modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp), fontWeight = FontWeight.Normal,
            color = textColor, maxLines = 5
        )

        Spacer(modifier = Modifier.height(12.dp))

        Divider(color = textColor.copy(alpha = 0.2f), thickness = 1.dp)

        Spacer(modifier = Modifier.height(12.dp))

        SectionTitle(title = "Tavsiya etilgan dori vositalar",viewModel)
        illness.recommendedPills.forEach {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                colors = CardDefaults.cardColors(containerColor = textColor),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(12.dp)
                        .background(textColor)
                ) {
                    Text(
                        text = it.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = mainColor
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = it.description, fontSize = 14.sp, color = mainColor)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "🕒 ${it.usage}",
                        fontSize = 13.sp,
                        color = mainColor.copy(alpha = 0.8f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        SectionTitle(title = "Uy sharoitida tavsiyalar",viewModel)
        illness.homeAdvice.forEach {
            Text(
                text = "• $it",
                fontSize = 15.sp,
                color = textColor,
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
                contentColor = mainColor,
                containerColor = textColor,
                disabledContentColor = mainColor,
                disabledContainerColor = textColor.copy(alpha = 0.05f)
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize().background(textColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tugatish",
                    fontSize = 18.sp,
                    color = mainColor,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun SectionTitle(title: String,viewModel: ThemeViewModel) {
    val isDarkTheme by viewModel.themeDark.collectAsState()
    val mainColor= if (isDarkTheme) MainColor else DMainColor
    val textColor=if (isDarkTheme) TextColor else DTextColor
    val textColor2=if(isDarkTheme) TextColor2 else DTextColor2
    val regColor=if (isDarkTheme) RegColor else DRegColor
    val aColor=if(isDarkTheme) AColor else DAColor

    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = textColor
    )
    Spacer(modifier = Modifier.height(8.dp))
}
