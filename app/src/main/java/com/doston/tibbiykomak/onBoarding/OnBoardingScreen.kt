package com.doston.tibbiykomak.onBoarding

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.doston.tibbiykomak.R
import com.doston.tibbiykomak.ui.theme.MainColor
import com.doston.tibbiykomak.ui.theme.TextColor
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(onFinish: () -> Unit) {
    val pagerState = rememberPagerState(initialPage = 0)
    val (selectedPage, setSelectedPage) = remember { mutableIntStateOf(0) }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            setSelectedPage(page)
        }
    }

    val context = LocalContext.current
    val listData = getOnBoardingData(context)
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(WindowInsets.systemBars.asPaddingValues())) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.background2), // replace with your image
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Foreground content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
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
                    Image(
                        painter = painterResource(id = listData[page].image),
                        contentDescription = listData[page].title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(260.dp)
                            .aspectRatio(1f)
                    )

                    Text(
                        text = listData[page].title,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(top = 40.dp)
                            .align(Alignment.CenterHorizontally),
                    )

                    Text(
                        text = listData[page].desc,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(top = 10.dp)

                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (selectedPage > 0) {
                    Button(
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(selectedPage - 1)
                            }
                        },
                        modifier = Modifier
                            .width(150.dp)
                            .height(45.dp)
                            .border(2.dp, MainColor, CircleShape),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = TextColor,
                            contentColor = MainColor
                        )
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.left_arrow),
                                contentDescription = "",
                                modifier = Modifier.size(20.dp),
                                tint = MainColor
                            )
                            Text(text = stringResource(R.string.oldingisi), color = MainColor, fontSize = 16.sp)
                        }
                    }
                }


                Button(
                    onClick = {

                        if (selectedPage == listData.size - 1) {
                            onFinish()
                        } else {
                            scope.launch {

                                pagerState.animateScrollToPage(selectedPage + 1)

                            }
                        }
                    },
                    modifier = Modifier
                        .width(150.dp)
                        .height(45.dp)
                        .border(2.dp, MainColor, CircleShape),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MainColor,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = if (selectedPage == listData.size - 1) stringResource(R.string.keyingisi) else stringResource(R.string.keyingisi) , color = TextColor, fontSize = 16.sp)
                    Icon(painter = painterResource(R.drawable.right_arrow), tint = TextColor, contentDescription = "", modifier = Modifier.size(20.dp))
                }
            }}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingPreview() {
    OnBoardingScreen {}
}
