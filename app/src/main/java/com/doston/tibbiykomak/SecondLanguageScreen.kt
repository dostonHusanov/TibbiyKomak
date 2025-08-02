package com.doston.tibbiykomak

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.doston.tibbiykomak.auth.LocaleManager
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

@Composable
fun SecondLanguageScreen(
    onLanguageSelected: (String) -> Unit,
    viewModel: ThemeViewModel,navController: NavController
) {

    var selectedLang by remember { mutableStateOf<String?>(null) }
    val isDarkTheme by viewModel.themeDark.collectAsState()
    val mainColor = if (isDarkTheme) MainColor else DMainColor
    val textColor = if (isDarkTheme) TextColor else DTextColor
    val textColor2 = if (isDarkTheme) TextColor2 else DTextColor2
    val regColor = if (isDarkTheme) RegColor else DRegColor
    val aColor = if (isDarkTheme) AColor else DAColor
    val languages = listOf(
        "uz" to "O'zbek",
        "en" to "English",
        "ru" to "Русский"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(mainColor)
            .padding(horizontal = 24.dp, vertical = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.select_language),
            fontSize = 24.sp,
            color = textColor,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        languages.forEach { (code, label) ->
            val isSelected = selectedLang == code
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .border(
                        width = if (isSelected) 2.dp else 1.dp,
                        color = if (isSelected) regColor else aColor,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .background(
                        color = if (isSelected) regColor.copy(alpha = 0.1f) else Color.Transparent,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable {
                        selectedLang = code
                    }
                    .padding(vertical = 16.dp, horizontal = 20.dp)
            ) {
                Text(
                    text = label,
                    color = textColor,
                    fontSize = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
        val context = LocalContext.current
        Button(
            onClick = {
                selectedLang?.let {
                    LocaleManager.setLocale(context, it)
                    onLanguageSelected(it)
                    (context as? Activity)?.recreate() // restart for locale to apply
                }
                navController.popBackStack()

            },
            enabled = selectedLang != null,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedLang != null) textColor else Color.Gray
            )
        ) {
            Text(text = "Next", fontSize = 16.sp, color = Color.Black)
        }
    }
}




