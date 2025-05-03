package com.doston.tibbiykomak.onBoarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.doston.tibbiykomak.R
import com.doston.tibbiykomak.ui.theme.MainColor
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreen(onStart: () -> Unit) {
    // Use LaunchedEffect to navigate after a delay
    LaunchedEffect(Unit) {
        delay(2000) // 2-second delay
     onStart()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(R.drawable.wel_text), contentDescription = "", modifier = Modifier.fillMaxWidth())
    }
}
