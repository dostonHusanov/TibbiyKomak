package com.doston.tibbiykomak.reminder

import android.graphics.Paint
import android.icu.number.Scale
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.doston.tibbiykomak.R
import com.doston.tibbiykomak.ui.theme.MainColor
import com.doston.tibbiykomak.ui.theme.TextColor
import com.doston.tibbiykomak.ui.theme.TextColor2

@Composable
fun ReminderScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize().background(MainColor)) {

        Column(
            Modifier
                .fillMaxSize()

        ) {

            LazyColumn(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Text(
                        text = "Bugungi Reja",
                        color = TextColor2,
                        fontSize = 22.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(15.dp, 0.dp, 15.dp, 0.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
                items(count = 15, itemContent = {
                    ReminderItem()
                })
            }
        }
        FloatingActionButton(
            onClick = { navController.navigate("pillScreen") },
            containerColor = TextColor,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = MainColor
            )
        }
    }
}
@Composable
fun ReminderItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(120.dp), shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MainColor)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xFF01A854)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "10:20",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(8.dp)
                        )
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "1/2", fontSize = 14.sp,
                                modifier = Modifier.padding(8.dp)
                            )
                            Text(
                                "Kun", fontSize = 14.sp,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                        Text(
                            "Istemol qilindi", fontSize = 14.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xFF01C763)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(6.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Card(
                                modifier = Modifier
                                    .size(55.dp)
                                    .padding(6.dp),
                                shape = CircleShape
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.pill),
                                    modifier = Modifier
                                        .size(55.dp)
                                        .padding(8.dp),
                                    contentDescription = ""
                                )
                            }
                        }
                        Column {
                            Text("Paratsitamol", fontSize = 22.sp)
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Text("Turi: ", fontSize = 14.sp)
                                Text("Kapsula", fontSize = 14.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReminderScreenPreview() {
    ReminderScreen(rememberNavController())
}