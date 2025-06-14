package com.doston.tibbiykomak.reminder

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.doston.tibbiykomak.ui.theme.MainColor
import com.doston.tibbiykomak.ui.theme.TextColor
import com.doston.tibbiykomak.ui.theme.TibbiyKomakTheme

@Composable
fun PillScreen(navController: NavController) {
    Scaffold { innerPadding ->


        Box(modifier = Modifier
            .fillMaxSize()
            .background(MainColor)
            .padding(innerPadding)) {


            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(modifier = Modifier) {
                    items(count = 15) {


                        PillItem()
                    }
                }
            }
            FloatingActionButton(
                onClick = { navController.navigate("pillAdd") },
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
}

@Composable
fun PillItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(TextColor, RoundedCornerShape(5.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(TextColor),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier
                .background(TextColor)
                .padding(5.dp)) {
                Text(
                    text = "Paratsitamol",
                    fontSize = 20.sp,
                    color = MainColor,
                    fontWeight = FontWeight.SemiBold
                )

                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "4 kun", fontSize = 18.sp, color = MainColor)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Kuniga 3 marta istemol qilinadi",
                        fontSize = 18.sp,
                        color = MainColor
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "popup menu",
                Modifier
                    .clickable { }
                    .padding(5.dp),
                tint = MainColor
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PillScreenPreview() {
    TibbiyKomakTheme {
        PillScreen(rememberNavController())
    }
}

