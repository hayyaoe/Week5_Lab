package com.hayyaoe.guessthenumber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.hayyaoe.guessthenumber.ui.theme.GuessTheNumberTheme
import com.hayyaoe.guessthenumber.ui.views.GPACounterView
import com.hayyaoe.guessthenumber.ui.views.GuessTheNumberView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuessTheNumberTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    GuessTheNumberView()
                    GPACounterView()
                }
            }
        }
    }
}



