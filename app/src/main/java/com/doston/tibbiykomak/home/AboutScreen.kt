package com.doston.tibbiykomak.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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
fun AboutScreen(navController: NavController, viewModel: ThemeViewModel) {
    val isDarkTheme by viewModel.themeDark.collectAsState()
    val mainColor = if (isDarkTheme) MainColor else DMainColor
    val textColor = if (isDarkTheme) TextColor else DTextColor
    val textColor2 = if (isDarkTheme) TextColor2 else DTextColor2
    val regColor = if (isDarkTheme) RegColor else DRegColor
    val aColor = if (isDarkTheme) AColor else DAColor
    Scaffold(
        containerColor = MainColor,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Ilova Haqida",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = textColor2
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = mainColor,
                    titleContentColor = textColor
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Orqaga",
                            tint = textColor2
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding).background(mainColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
                    .background(mainColor)
            ) {
                SectionHeader(
                    "Tibbiy Ko'mak — Sizning Shaxsiy Sog‘liqni Saqlash Yordamchingiz",
                    viewModel
                )
                Spacer(modifier = Modifier.height(10.dp))
                SectionText(
                    viewModel = viewModel,
                    text = "Tibbiy Ko'mak — bu sizning kundalik sog‘lig‘ingizni nazorat qilishda yordam beradigan ilova. Dori eslatmalari, kasalliklar haqida ma'lumotlar va ko‘plab foydali funksiyalarni taqdim etadi."
                )

                Spacer(modifier = Modifier.height(20.dp))
                SectionHeader("Asosiy xususiyatlar", viewModel)

                val features = listOf(
                    "Dori eslatmalari.",
                    "Kasalliklar haqida ma'lumot.",
                    "Shaxsiy profil.",
                    "Bog'lanish funksiyasi.",
                    "Tungi va kunduzgi rejimlar."
                )
                BulletList(items = features, viewModel)

                Spacer(modifier = Modifier.height(20.dp))
                SectionHeader("Kimlar uchun mo‘ljallangan", viewModel)

                val people = listOf(
                    "Doimiy dori iste'mol qiluvchi insonlar uchun.",
                    "Yoshi katta, eslatmalarga muhtoj foydalanuvchilar uchun.",
                    "Farzandlariga dori eslatmalarini sozlamoqchi bo‘lgan ota-onalar uchun.",
                    "Sog‘lig‘ini nazorat qilmoqchi bo‘lgan foydalanuvchilar uchun.",
                    "Oddiy va o‘zbek tilidagi ilovani afzal ko‘radiganlar uchun.",
                    "Murakkab tibbiy ilovalardan charchagan foydalanuvchilar uchun.",
                    "Nogironligi bor va eslatmaga ehtiyoji bo‘lgan insonlar uchun.",
                    "Tibbiy ma’lumotlarga tezkor kirishni xohlaydiganlar uchun."
                )
                BulletList(items = people, viewModel)

                Spacer(modifier = Modifier.height(20.dp))
                SectionHeader("Nima uchun Tibbiy Ko'mak?", viewModel)

                val reasons = listOf(
                    "Oson va tushunarli dizayn.",
                    "To‘liq o‘zbek tilida.",
                    "Internetga bog‘liq bo‘lmagan holda ishlaydi.",
                    "Sog‘lig‘ingiz siz uchun muhim bo‘lgani kabi, biz uchun ham muhim."
                )
                BulletList(items = reasons, viewModel)

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Ilova versiyasi: 1.0.0",
                    fontSize = 14.sp,
                    color = textColor2
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "© 2025 Tibbiy Ko'mak. Barcha huquqlar himoyalangan.",
                    fontSize = 12.sp,
                    color = textColor2
                )
            }
        }
    }
}

@Composable
fun SectionHeader(title: String, viewModel: ThemeViewModel) {
    val isDarkTheme by viewModel.themeDark.collectAsState()
    val mainColor = if (isDarkTheme) MainColor else DMainColor
    val textColor = if (isDarkTheme) TextColor else DTextColor
    val textColor2 = if (isDarkTheme) TextColor2 else DTextColor2
    val regColor = if (isDarkTheme) RegColor else DRegColor
    val aColor = if (isDarkTheme) AColor else DAColor
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        color = textColor2,
        modifier = Modifier
    )
}

@Composable
fun SectionText(text: String, viewModel: ThemeViewModel) {
    val isDarkTheme by viewModel.themeDark.collectAsState()
    val mainColor = if (isDarkTheme) MainColor else DMainColor
    val textColor = if (isDarkTheme) TextColor else DTextColor
    val textColor2 = if (isDarkTheme) TextColor2 else DTextColor2
    val regColor = if (isDarkTheme) RegColor else DRegColor
    val aColor = if (isDarkTheme) AColor else DAColor
    Text(
        text = text,
        fontSize = 16.sp,
        color = textColor,
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)
    )
}

@Composable
fun BulletList(items: List<String>, viewModel: ThemeViewModel) {
    val isDarkTheme by viewModel.themeDark.collectAsState()
    val mainColor = if (isDarkTheme) MainColor else DMainColor
    val textColor = if (isDarkTheme) TextColor else DTextColor
    val textColor2 = if (isDarkTheme) TextColor2 else DTextColor2
    val regColor = if (isDarkTheme) RegColor else DRegColor
    val aColor = if (isDarkTheme) AColor else DAColor
    items.forEach { item ->
        Row(modifier = Modifier.padding(horizontal = 4.dp), verticalAlignment = Alignment.Top) {
            Text(
                text = " • ",
                fontSize = 16.sp,
                color = textColor,
                modifier = Modifier.padding(vertical = 2.dp), fontWeight = FontWeight.Bold
            )
            Text(
                text = item,
                fontSize = 16.sp,
                color = textColor,
                modifier = Modifier.padding(vertical = 2.dp)
            )
        }
    }
}
