package com.amoronk.movemate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.amoronk.movemate.navigate.NavHostContainer
import com.amoronk.movemate.ui.theme.MoniePointChallengeTheme
import com.amoronk.movemate.ui.theme.Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    MoniePointChallengeTheme(theme = Theme.FOLLOW_SYSTEM.themeValue) {
        val navController = rememberNavController()
        NavHostContainer(navController = navController)
    }
}
