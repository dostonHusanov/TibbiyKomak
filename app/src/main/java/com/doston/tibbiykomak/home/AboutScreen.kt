package com.doston.tibbiykomak.home

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.doston.tibbiykomak.ui.theme.MainColor
import com.doston.tibbiykomak.ui.theme.TextColor
import com.doston.tibbiykomak.ui.theme.TibbiyKomakTheme

@Composable
fun AboutScreen(navController: NavController) {
    Column(
        modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues()).padding(WindowInsets.systemBars.asPaddingValues())
            .fillMaxSize()
            .background(MainColor),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                modifier = Modifier
                    .shadow(elevation = 0.dp, shape = CircleShape, clip = true)
                    .size(26.dp)
                    .clickable { navController.popBackStack() },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
            )

            Text(text = "Ilova Haqida", fontSize = 22.sp, color = Color.Black)
        }
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            item {
                Text(
                    text = "Tibbiy Ko'mak — Sizning Shaxsiy Sog‘liqni Saqlash Yordamchingiz",
                    color = TextColor,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
            }
            item {
                Text(
                    text = "Tibbiy Ko'mak — bu foydalanuvchi uchun qulay sog‘liqni saqlash ilovasi bo‘lib, kunlik sog‘lig‘ingizni nazorat qilishda yordam beradi. Dori eslatmalari, kasalliklar haqida ma’lumotlar va foydali funksiyalar bir joyda jamlangan.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = TextColor,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
            }
            item {
                Text(
                    text = "\uD83C\uDF1F Asosiy xususiyatlar:\n" +
                            "\uD83D\uDC8A Dori eslatmalari — Doringizni ichishni unutmang! Har kuni eslatmalarni sozlang va davolanish tartibingizni nazorat qiling.\n" +
                            "\n" +
                            "\uD83D\uDCDA Kasalliklar haqida ma'lumot — Eng ko‘p uchraydigan kasalliklar va ularning davosi haqida sodda va tushunarli tarzda o‘qing.\n" +
                            "\n" +
                            "\uD83D\uDCC7 Shaxsiy profil — Yoshingiz, ism-familiyangiz va telefon raqamingizni saqlang, shaxsiylashtirilgan xizmatlardan foydalaning.\n" +
                            "\n" +
                            "\uD83D\uDCE8 Bog‘lanish bo‘limi — Taklif yoki savollaringiz bo‘lsa, biz bilan osongina bog‘laning.\n" +
                            "\n" +
                            "ℹ\uFE0F Ilova haqida — Ilovaning qanday ishlashi va sizga qanday foyda keltirishi haqida ma'lumot oling.\n" +
                            "\n",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = TextColor,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
            }
            item {
                Text(
                    text = " Kimlar uchun mo‘ljallangan:\n" +
                            "Doimiy dori ichadigan foydalanuvchilar\n" +
                            "\n" +
                            "Keksalar va eslatmalarga muhtoj bo‘lganlar\n" +
                            "\n" +
                            "Soddaligi va o‘zbek tilida bo‘lgan ilovalarni afzal ko‘radiganlar\n" +
                            "\n",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = TextColor,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
            }
            item {
                Text(
                    text = "\uD83D\uDEE1\uFE0F Nima uchun aynan Tibbiy Ko'mak?\n" +
                            "Oson va tushunarli dizayn\n" +
                            "\n" +
                            "To‘liq o‘zbek tilida\n" +
                            "\n" +
                            "Internetga bog‘liq bo‘lmagan holda ishlaydi\n" +
                            "\n" +
                            "Sog‘lig‘ingiz siz uchun muhim bo‘lgani kabi, biz uchun ham muhim\n" +
                            "\n",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = TextColor,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )

            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Spacer(modifier = Modifier.height(10.dp))
        Spacer(modifier = Modifier.height(10.dp))
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "\uD83D\uDEE1\uFE0F Nima uchun aynan Tibbiy Ko'mak?\n" +
                    "Oson va tushunarli dizayn\n" +
                    "\n" +
                    "To‘liq o‘zbek tilida\n" +
                    "\n" +
                    "Internetga bog‘liq bo‘lmagan holda ishlaydi\n" +
                    "\n" +
                    "Sog‘lig‘ingiz siz uchun muhim bo‘lgani kabi, biz uchun ham muhim\n" +
                    "\n",
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = TextColor,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    TibbiyKomakTheme { AboutScreen(rememberNavController()) }
}