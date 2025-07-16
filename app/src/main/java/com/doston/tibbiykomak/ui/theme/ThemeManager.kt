package com.doston.tibbiykomak.ui.theme


import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ThemeManager(private val context: Context) {
    private val _isDarkMode = MutableStateFlow(getDarkModePreference())
    val isDarkMode: StateFlow<Boolean> = _isDarkMode

    private fun getDarkModePreference(): Boolean {
        val sharedPreferences = context.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("dark_mode", false)
    }

    fun toggleTheme() {
        val newValue = !_isDarkMode.value
        _isDarkMode.value = newValue
        saveDarkModePreference(newValue)
    }

    fun setDarkMode(isDark: Boolean) {
        _isDarkMode.value = isDark
        saveDarkModePreference(isDark)
    }

    private fun saveDarkModePreference(isDark: Boolean) {
        val sharedPreferences = context.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("dark_mode", isDark).apply()
    }
}

@Composable
fun rememberThemeManager(context: Context): ThemeManager {
    return androidx.compose.runtime.remember { ThemeManager(context) }
}

@Composable
fun ThemeManager.isDarkModeEnabled(): Boolean {
    val isDark by isDarkMode.collectAsState()
    return isDark
}