package com.doston.tibbiykomak.onBoarding


import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@SuppressLint("InvalidColorHexValue")
@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(onFinish: () -> Unit) {
    val pagerState = rememberPagerState(initialPage = 0)
    val (selectedPage, setSelectedPage) = remember { mutableIntStateOf(0) }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            setSelectedPage(page)
        }
    }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Horizontal Pager (Dots Indicator)
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            for (i in listData.indices) {
                Box(
                    modifier = Modifier
                        .width(76.dp)
                        .padding(2.dp)
                        .size(2.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(
                            if (i == selectedPage) Color.Blue
                            else Color(0xF0C0C0C0)
                        )
                )
            }
        }

        // Horizontal Pager
        HorizontalPager(
            count = listData.size,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                // Display the image
                Image(
                    painter = painterResource(id = listData[page].image),
                    contentDescription = listData[page].title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(300.dp)
                        .aspectRatio(1f)
                )

                // Display the title
                Text(
                    text = listData[page].title,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .align(Alignment.Start),
                )

                Text(
                    text = listData[page].desc,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(
                onClick = {
                    if (selectedPage > 0) {
                        scope.launch {
                            pagerState.animateScrollToPage(selectedPage - 1)
                        }
                    }
                },
                enabled = selectedPage > 0,
                modifier = Modifier.width(180.dp)
                    .padding(16.dp)
                    .height(40.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2440FF),
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = if (selectedPage == listData.size + 1) "Oldingisi" else "Oldingisi",
                    color = Color.White
                )
            }
            Button(
                onClick = {
                    if (selectedPage == listData.size - 1) {
                        onFinish() // Corrected from `onFinish` to `onFinish()`
                    } else {
                        scope.launch {
                            pagerState.animateScrollToPage(selectedPage + 1)
                        }
                    }
                },
                modifier = Modifier.width(180.dp)
                    .padding(16.dp)
                    .height(40.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2440FF),
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = if (selectedPage == listData.size - 1) "Davom etish" else "Keyingisi",
                    color = Color.White
                )
            }
        }
        // Navigation Button

    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingPreview() {
    OnBoardingScreen { }

}
