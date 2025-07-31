package com.doston.tibbiykomak.home

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.doston.tibbiykomak.R
import com.doston.tibbiykomak.data.getIllnessList
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

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    categoryId: Int,
    modifier: Modifier = Modifier,viewModel: ThemeViewModel
) {
val context= LocalContext.current

    val illnessList = getIllnessList(categoryId,context)
    val grouped = illnessList.groupBy { it.category }
    val nestedScrollInterop = rememberNestedScrollInteropConnection()
    val isDarkTheme by viewModel.themeDark.collectAsState()
    val mainColor= if (isDarkTheme) MainColor else DMainColor
    val textColor=if (isDarkTheme) TextColor else DTextColor
    val textColor2=if(isDarkTheme) TextColor2 else DTextColor2
    val regColor=if (isDarkTheme) RegColor else DRegColor
    val aColor=if(isDarkTheme) AColor else DAColor


    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(mainColor)
            .nestedScroll(nestedScrollInterop)
    ) {
        grouped.forEach { (category, illnesses) ->
            item {

                Text(
                    text = category,
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 12.dp),
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
                            }, viewModel = viewModel
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
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
    onClick: () -> Unit = {},viewModel: ThemeViewModel
) {
    val isDarkTheme by viewModel.themeDark.collectAsState()

    val mainColor= if (isDarkTheme) MainColor else DMainColor
    val textColor=if (isDarkTheme) TextColor else DTextColor
    val textColor2=if(isDarkTheme) TextColor2 else DTextColor2
    val regColor=if (isDarkTheme) RegColor else DRegColor
    val aColor=if(isDarkTheme) AColor else DAColor

    Card(
        modifier = modifier
            .height(140.dp)
            .width(280.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = textColor
                )
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
                        color = mainColor
                       // color = TextColor2
                    )
                    Text(
                        text = desc,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        maxLines = 3,
                        color = mainColor,
                       // color = Color(0xFF004B1C),
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
                            containerColor =mainColor,
                            contentColor =textColor
                        ),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(stringResource(R.string.ko_rish), fontSize = 15.sp, textAlign = TextAlign.Center)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }
                Column(modifier = Modifier
                    .background(color = mainColor, shape = CircleShape)
                    .size(80.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = "Image for $title",
                        modifier = Modifier
                            .size(70.dp)
                            .weight(1f)
                            .padding(2.dp)
                    )
                }

            }
        }
    }
}


