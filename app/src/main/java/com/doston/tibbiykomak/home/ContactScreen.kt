package com.doston.tibbiykomak.home

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(navController: NavController,viewModel:ThemeViewModel) {
    val context = LocalContext.current
    var fullName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var messageText by remember { mutableStateOf("") }
    val isDarkTheme by viewModel.themeDark.collectAsState()
    val mainColor= if (isDarkTheme) MainColor else DMainColor
    val textColor=if (isDarkTheme) TextColor else DTextColor
    val textColor2=if(isDarkTheme) TextColor2 else DTextColor2
    val regColor=if (isDarkTheme) RegColor else DRegColor
    val aColor=if(isDarkTheme) AColor else DAColor

    Scaffold(
        containerColor = MainColor,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Biz bilan bog'lanish",
                        fontSize = 22.sp,
                        color = textColor2
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Orqaga",
                            tint = textColor2
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = mainColor
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(mainColor)
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text("To'liq ismingiz", color = textColor) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = textColor2,
                    unfocusedBorderColor = textColor,
                    focusedLabelColor = textColor2,
                    unfocusedLabelColor = textColor,
                    cursorColor = textColor2
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Telefon raqamingiz", color = textColor) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = textColor2,
                    unfocusedBorderColor = textColor,
                    focusedLabelColor = textColor2,
                    unfocusedLabelColor = textColor,
                    cursorColor = textColor2
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = messageText,
                onValueChange = { messageText = it },
                label = { Text("Xabaringiz", color = textColor) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = textColor2,
                    unfocusedBorderColor = textColor,
                    focusedLabelColor = textColor2,
                    unfocusedLabelColor = textColor,
                    cursorColor = textColor2
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    if (fullName.isNotEmpty() && phoneNumber.isNotEmpty() && messageText.isNotEmpty()) {
                        val message =
                            "Salom! Mening ismim: $fullName\nTelefon raqamim: $phoneNumber\nXabar: $messageText"
                        val encodedMessage = Uri.encode(message)

                        val telegramUsername = "Husanov_Doston"
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://t.me/$telegramUsername?text=$encodedMessage")
                        )
                        context.startActivity(intent)
                    } else {
                        Toast.makeText(
                            context,
                            "Maydonlar bo'sh bo'lmasligi kerak",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = textColor,
                    contentColor = mainColor
                )
            ) {
                Text(text = "Xabar yuborish", fontSize = 16.sp, color = mainColor)
            }
        }
    }
}
