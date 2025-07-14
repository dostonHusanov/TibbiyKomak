package com.doston.tibbiykomak.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.doston.tibbiykomak.R
import com.doston.tibbiykomak.ui.theme.MainColor
import com.doston.tibbiykomak.ui.theme.TextColor
import com.doston.tibbiykomak.ui.theme.TextColor2
import com.doston.tibbiykomak.ui.theme.TibbiyKomakTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(
        containerColor = MainColor,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Ilova Haqida",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextColor2
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MainColor,
                    titleContentColor = TextColor
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Orqaga",
                            tint = TextColor2
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            // App Logo
            Icon(
                painter = painterResource(R.drawable.wel_text),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp),
                tint = TextColor
            )

            Spacer(modifier = Modifier.height(16.dp))
            SectionHeader("🩺 Tibbiy Ko'mak — Sizning Shaxsiy Sog‘liqni Saqlash Yordamchingiz")

            SectionText("Tibbiy Ko'mak — bu sizning kundalik sog‘lig‘ingizni nazorat qilishda yordam beradigan ilova. Dori eslatmalari, kasalliklar haqida ma'lumotlar va ko‘plab foydali funksiyalarni taqdim etadi.")

            Spacer(modifier = Modifier.height(20.dp))
            SectionHeader("🔧 Asosiy xususiyatlar")

            val features = listOf(
                "Dori eslatmalari.",
                "Kasalliklar haqida ma'lumot.",
                "Shaxsiy profil.",
                "Bog'lanish funksiyasi.",
                "Tungi va kunduzgi rejimlar."
            )
            BulletList(items = features)

            Spacer(modifier = Modifier.height(20.dp))
            SectionHeader("🎯 Kimlar uchun mo‘ljallangan")

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
            BulletList(items = people)

            Spacer(modifier = Modifier.height(20.dp))
            SectionHeader("🛡️ Nima uchun Tibbiy Ko'mak?")

            val reasons = listOf(
                "Oson va tushunarli dizayn.",
                "To‘liq o‘zbek tilida.",
                "Internetga bog‘liq bo‘lmagan holda ishlaydi.",
                "Sog‘lig‘ingiz siz uchun muhim bo‘lgani kabi, biz uchun ham muhim."
            )
            BulletList(items = reasons)

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Ilova versiyasi: 1.0.0",
                fontSize = 14.sp,
                color = TextColor2
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "© 2025 Tibbiy Ko'mak. Barcha huquqlar himoyalangan.",
                fontSize = 12.sp,
                color = TextColor2
            )
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        color = TextColor,
        modifier = Modifier.padding(bottom = 4.dp)
    )
}

@Composable
fun SectionText(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        color = TextColor2,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun BulletList(items: List<String>) {
    items.forEach { item ->
        Text(
            text = "• $item",
            fontSize = 16.sp,
            color = TextColor,
            modifier = Modifier.padding(vertical = 2.dp)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun AboutScreenPreview(){
    TibbiyKomakTheme {
        AboutScreen(rememberNavController())
    }
}