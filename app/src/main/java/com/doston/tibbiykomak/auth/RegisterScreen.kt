package com.doston.tibbiykomak.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.doston.tibbiykomak.R
import com.doston.tibbiykomak.data.User
import com.doston.tibbiykomak.database.UserDatabaseHelper
import com.doston.tibbiykomak.ui.theme.MainColor
import com.doston.tibbiykomak.ui.theme.RegColor
import com.doston.tibbiykomak.ui.theme.TextColor

@Composable
fun RegisterScreen(onFinish: () -> Unit) {
    val name = remember { mutableStateOf("") }
    val surname = remember { mutableStateOf("") }
    val age = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(WindowInsets.systemBars.asPaddingValues())) {

            Image(
                painter = painterResource(id = R.drawable.background1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Ro’yhatdan o’ting",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = TextColor
            )

            RoundedTextField(label = "Ism", value = name.value, onValueChange = { name.value = it })
            RoundedTextField(label = "Familya", value = surname.value, onValueChange = { surname.value = it })
            RoundedTextField(
                label = "Yosh",
                value = age.value,
                onValueChange = { age.value = it },
                keyboardType = KeyboardType.Number
            )

            RoundedTextField(
                label = "Telefon raqam",
                value = phoneNumber.value,
                onValueChange = { phoneNumber.value = it },
                keyboardType = KeyboardType.Phone
            )
            val context = LocalContext.current
            val dbHelper = remember { UserDatabaseHelper(context) }
            Button(
                onClick = { val user = User(
                    name = name.value,
                    surname = surname.value,
                    age = age.value.toIntOrNull() ?: 0,
                    phoneNumber = phoneNumber.value
                )
                    if(name.value.isNotEmpty()&&surname.value.isNotEmpty()&&age.value.isNotEmpty()&&phoneNumber.value.isNotEmpty()){
                        dbHelper.insertUser(user)
                        onFinish()
                    }else{
                        Toast.makeText(context, "Iltimos, barcha kataklarni to'ldiring", Toast.LENGTH_SHORT).show()
                    }
                     },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = TextColor),
                shape = RoundedCornerShape(50)
            ) {
                Text("Davom etish", fontSize = 18.sp, fontWeight = FontWeight.Medium, color = MainColor)
            }
        }
    }
}}
@Composable
fun RoundedTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 0.dp),
        shape = RoundedCornerShape(50),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = RegColor,
            unfocusedContainerColor = RegColor,
            disabledContainerColor = RegColor,
            focusedIndicatorColor = TextColor,
            unfocusedIndicatorColor = Color.Gray,
            cursorColor = Color.Black,
            focusedTextColor = TextColor,
            unfocusedTextColor = TextColor
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}

