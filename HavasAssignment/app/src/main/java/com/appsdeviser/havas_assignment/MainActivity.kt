package com.appsdeviser.havas_assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.appsdeviser.component.core.AppTheme
import com.appsdeviser.havas_assignment.examples.ComponentsExamples

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val isSystemDark = isSystemInDarkTheme()
            var darkTheme by rememberSaveable { mutableStateOf(isSystemDark) }

            AppTheme(
                isDarkTheme = darkTheme
            ) {
                ComponentsExamples(
                    isDarkTheme = darkTheme,
                    onDarkThemeToggle = { darkTheme = it }
                )
            }
        }
    }
}