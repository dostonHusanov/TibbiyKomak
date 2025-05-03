package com.doston.tibbiykomak.main

import android.content.pm.ActivityInfo.WindowLayout
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import com.doston.tibbiykomak.R
import com.doston.tibbiykomak.navigation.MainNav

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            window.statusBarColor = ContextCompat.getColor(this, R.color.background)
window.navigationBarColor=ContextCompat.getColor(this, R.color.background)

            MainNav(context = applicationContext)



        }
    }
}



