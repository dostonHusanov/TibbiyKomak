package com.doston.tibbiykomak.home

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.doston.tibbiykomak.R
import com.doston.tibbiykomak.database.IllnessViewModel
import com.doston.tibbiykomak.ui.theme.*

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    categoryId: Int,
    modifier: Modifier = Modifier,
    themeViewModel: ThemeViewModel,
    illnessViewModel: IllnessViewModel = viewModel()
) {
    val context = LocalContext.current
    val nestedScrollInterop = rememberNestedScrollInteropConnection()

    val isDarkTheme by themeViewModel.themeDark.collectAsState()
    val mainColor = if (isDarkTheme) MainColor else DMainColor
    val textColor = if (isDarkTheme) TextColor else DTextColor
    val textColor2 = if (isDarkTheme) TextColor2 else DTextColor2
    val regColor = if (isDarkTheme) RegColor else DRegColor
    val aColor = if (isDarkTheme) AColor else DAColor

    // Observe data from ViewModel
    val illnessList by illnessViewModel.illnesses.collectAsState()
    val isLoading by illnessViewModel.isLoading.collectAsState()
    val error by illnessViewModel.error.collectAsState()

    // Load illnesses when screen is first composed
    LaunchedEffect(categoryId) {
        // If categoryId is 0 or -1, load all categories; otherwise load specific category
        if (categoryId == 0 || categoryId == -1) {
            illnessViewModel.loadAllCategories()
        } else {
            illnessViewModel.loadIllnesses(categoryId)
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(mainColor)
    ) {
        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = textColor
                )
            }
            error != null -> {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = error ?: "Unknown error",
                        color = textColor,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            if (categoryId == 0 || categoryId == -1) {
                                illnessViewModel.loadAllCategories()
                            } else {
                                illnessViewModel.loadIllnesses(categoryId)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = textColor,
                            contentColor = mainColor
                        )
                    ) {
                        Text("Retry")
                    }
                }
            }
            illnessList.isEmpty() -> {
                Text(
                    text = stringResource(R.string.no_data_available),
                    modifier = Modifier.align(Alignment.Center),
                    color = textColor
                )
            }
            else -> {
                val grouped = illnessList.groupBy { it.category }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(nestedScrollInterop)
                ) {
                    grouped.forEach { (category, illnesses) ->
                        item {
                            Text(
                                text = category,
                                fontSize = 22.sp,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                color = textColor
                            )
                        }

                        item {
                            Spacer(modifier = Modifier.height(4.dp))
                            LazyRow(
                                contentPadding = PaddingValues(horizontal = 12.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                            ) {
                                items(illnesses, key = { it.problem }) { illness ->
                                    HomeItem(
                                        title = illness.problem,
                                        desc = illness.description,
                                        imageRes = illness.image,
                                        onClick = {
                                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                                "illness",
                                                illness
                                            )
                                            navController.navigate("infoScreen")
                                        },
                                        viewModel = themeViewModel
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeItem(
    title: String,
    desc: String,
    imageRes: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    viewModel: ThemeViewModel
) {
    val isDarkTheme by viewModel.themeDark.collectAsState()

    val mainColor = if (isDarkTheme) MainColor else DMainColor
    val textColor = if (isDarkTheme) TextColor else DTextColor

    Card(
        modifier = modifier
            .height(140.dp)
            .width(280.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = textColor)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 8.dp, horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(2f),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        maxLines = 1,
                        color = mainColor,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = desc,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        maxLines = 3,
                        color = mainColor,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Button(
                        modifier = Modifier
                            .height(30.dp)
                            .width(124.dp),
                        onClick = onClick,
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = mainColor,
                            contentColor = textColor
                        ),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            stringResource(R.string.ko_rish),
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }
                Column(
                    modifier = Modifier
                        .background(color = mainColor, shape = CircleShape)
                        .size(80.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = "Image for $title",
                        modifier = Modifier
                            .size(70.dp)
                            .padding(2.dp)
                    )
                }
            }
        }
    }
}