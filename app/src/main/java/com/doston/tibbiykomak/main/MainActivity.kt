package com.doston.tibbiykomak.main

import android.Manifest
import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.doston.tibbiykomak.R
import com.doston.tibbiykomak.auth.LocaleManager
import com.doston.tibbiykomak.navigation.MainNav
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
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Logger

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            FirebaseDatabase.getInstance().setLogLevel(Logger.Level.DEBUG)
            val viewModel: ThemeViewModel = viewModel()
            val isDarkTheme by viewModel.themeDark.collectAsState()
            MainNav(context = applicationContext, viewModel)
            requestAlarmPermissions()
            if (!isDarkTheme) {
                window.statusBarColor = ContextCompat.getColor(this, R.color.dMainColor)
                window.navigationBarColor = ContextCompat.getColor(this, R.color.black)


            } else {
                window.statusBarColor = ContextCompat.getColor(this, R.color.mainColor)
                window.navigationBarColor = ContextCompat.getColor(this, R.color.white)
            }


        }
    }

    private fun requestAlarmPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            if (!alarmManager.canScheduleExactAlarms()) {
                val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                intent.data = Uri.parse("package:$packageName")
                startActivity(intent)
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1
                )
            }
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleManager.applyStoredLocale(newBase))
    }
}




