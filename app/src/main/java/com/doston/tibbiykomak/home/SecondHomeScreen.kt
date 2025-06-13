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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.painterResource
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
import com.doston.tibbiykomak.ui.theme.MainColor
import com.doston.tibbiykomak.ui.theme.TextColor
import com.doston.tibbiykomak.ui.theme.TextColor2
import com.doston.tibbiykomak.ui.theme.TibbiyKomakTheme
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SecondHomeScreen(
    navController: NavController,

    categoryId: Int,
    modifier: Modifier = Modifier
) {
    val illnessList = getIllnessList(categoryId)
    val grouped = illnessList.groupBy { it.category }
    val nestedScrollInterop = rememberNestedScrollInteropConnection()
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MainColor).nestedScroll(nestedScrollInterop)
    ) {
        grouped.forEach { (category, illnesses) ->
            item {
                Text(
                    text = category,
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                )
            }

            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(illnesses, key = { it.problem }) { illness ->
                        SecondHomeItem(
                            title = illness.problem,
                            desc = illness.description,
                            imageRes = R.drawable.headache,
                            onClick = {
                                navController.currentBackStackEntry?.savedStateHandle?.set("illness", illness)
                                navController.navigate("infoScreen")
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun SecondHomeItem(
    title: String,
    desc: String,
    imageRes: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .height(135.dp)
            .width(280.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(AColor, Color(0xFF00BF33))
                    )
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
                        color = TextColor2
                    )
                    Text(
                        text = desc,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        maxLines = 3,
                        color = Color(0xFF004B1C),
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
                            containerColor = MainColor,
                            contentColor = TextColor
                        ),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text("Ko'rish", fontSize = 15.sp, textAlign = TextAlign.Center)
                    }
                }
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "Image for $title",
                    modifier = Modifier
                        .size(80.dp)
                        .weight(1f)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SecondHomeItemPreview() {
    HomeItem(
        title = "Bosh og'rig'i",
        imageRes = R.drawable.headache,
        desc = "Bosh og'rig'i deyarli barchamiz bir vaqtning o'zida boshdan kechirgan hayratlanarli keng tarqalgan tibbiy shikoyatdir."
    )
}

@Preview(showBackground = true)
@Composable
fun SecondHomeScreenPreview() {
    TibbiyKomakTheme {
        SecondHomeScreen(
            navController = rememberNavController(),
            categoryId = 1
        )
    }
}
