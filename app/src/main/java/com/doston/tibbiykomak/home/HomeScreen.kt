package com.doston.tibbiykomak.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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
                text = "Salom, $userName 👋",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,modifier = modifier
                    .fillMaxSize().padding(start = 12.dp)

            )
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "O'zingizdagi belgilar bo‘yicha dori va maslahatlarni ko‘ring:",
                style = MaterialTheme.typography.bodyMedium,modifier = modifier
                    .fillMaxSize().padding(start = 12.dp, end = 8.dp)

            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        grouped.forEach { (category, illnesses) ->
            item {
                Text(
                    text = category,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 12.dp)
                )
            }

            item {
                LazyRow {
                    items(illnesses) { illness ->
                        Card(
                            modifier = Modifier
                                .width(280.dp)
                                .padding(end = 6.dp, start = 12.dp),
                            shape = RoundedCornerShape(12.dp),

                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = illness.problem,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                illness.recommendedPills.forEach { pill ->
                                    Text(
                                        text = "💊 ${pill.name}",
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Text(
                                        text = pill.description,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                    Text(
                                        text = "🕒 ${pill.usage}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.Gray
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                }

                                Text(
                                    text = "🏠 Uy sharoitida maslahatlar:",
                                    fontWeight = FontWeight.SemiBold,
                                    style = MaterialTheme.typography.bodySmall
                                )
                                illness.homeAdvice.forEach {
                                    Text(
                                        text = "• $it",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
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
