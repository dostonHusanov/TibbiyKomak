package com.doston.tibbiykomak.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.doston.tibbiykomak.R
import com.doston.tibbiykomak.data.getIllnessList
import com.doston.tibbiykomak.ui.theme.TibbiyKomakTheme

@Composable
fun HomeScreen(
    navController: NavController,
    userName: String,
    categoryId: Int,
    modifier: Modifier = Modifier
) {
    val illnessList = getIllnessList(categoryId)
    val grouped = illnessList.groupBy { it.category }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()

    ) {
        item {

            Text(
                text = "Salom, $userName",
                color =  Color.Magenta,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,modifier = modifier
                    .fillMaxSize().padding(start = 12.dp)

            )
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "O'zingizdagi belgilar bo‘yicha dori va maslahatlarni ko‘ring",
                style = MaterialTheme.typography.bodyMedium,modifier = modifier
                    .fillMaxSize().padding(start = 12.dp, end = 8.dp)
            )
            Spacer(modifier = Modifier.height(14.dp))
        }

        grouped.forEach { (category, illnesses) ->
            item {
                Text(
                    text = category,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding( horizontal = 12.dp)
                )
            }

            item {
                LazyRow {
                    items(illnesses) { illness ->

                        Column(modifier = Modifier.padding(16.dp)) {
                            HomeItem(title = illness.problem, desc = "Bosh og'rig'i deyarli barchamiz bir vaqtning o'zida boshdan kechirgan hayratlanarli keng tarqalgan tibbiy shikoyatdir. ",R.drawable.headache)
                        }

                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
@Composable
fun HomeItem(title: String, desc: String, imageRes: Int,onClick :( )->Unit={}) {
    Card(
        modifier = Modifier
            .height(160.dp)
            .width(280.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Cyan,
            contentColor = Color.Black
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Image for $title",
                modifier = Modifier
                    .size(80.dp)
                    .weight(1f)
            )

            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = desc,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    maxLines = 3,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(6.dp))

                Button(
                    onClick = { onClick },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                        contentColor = Color.White
                    )
                ) {
                    Text("Ko'rish")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeItemPreview(){
    HomeItem(title = "Bosh og'rig'i", imageRes = R.drawable.headache, desc = "Bosh og'rig'i deyarli barchamiz bir vaqtning o'zida boshdan kechirgan hayratlanarli keng tarqalgan tibbiy shikoyatdir.")
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    TibbiyKomakTheme {
        HomeScreen(
            navController = rememberNavController(),
            userName = "Doston",
            categoryId = 1
        )
    }
}
