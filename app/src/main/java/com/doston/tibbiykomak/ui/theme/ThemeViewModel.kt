package com.doston.tibbiykomak.ui.theme

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ThemeViewModel(application: Application):AndroidViewModel(application) {
    private val sharedPreferences = application.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)
    private val _themeDark = MutableStateFlow(
        sharedPreferences.getBoolean("is_dark_theme", true)
    )
   val themeDarkState = mutableStateOf(true)
    val themeDark = _themeDark.asStateFlow()

    fun setTheme(isDark: Boolean) {
        _themeDark.value = isDark
        themeDarkState.value = isDark

        sharedPreferences.edit()
            .putBoolean("is_dark_theme", isDark)
            .apply()
    }
}