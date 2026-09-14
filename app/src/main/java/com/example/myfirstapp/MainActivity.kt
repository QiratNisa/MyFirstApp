package com.example.myfirstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TimerApp()
        }
    }
}

@Composable
fun TimerApp() {

    var seconds by rememberSaveable { mutableStateOf(60) }
    var isRunning by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(isRunning) {

        while (isRunning && seconds > 0) {

            delay(1000)

            seconds--

            if (seconds == 0) {
                isRunning = false
            }
        }
    }

    val minutes = seconds / 60
    val remainingSeconds = seconds % 60

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "MY TIMER",
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(35.dp))

        // Timer Circle
        Column(
            modifier = Modifier
                .size(230.dp)
                .background(
                    color = Color.White,
                    shape = CircleShape
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = String.format(
                    "%02d:%02d",
                    minutes,
                    remainingSeconds
                ),
                fontSize = 48.sp
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Button(
                onClick = {
                    isRunning = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3)
                )
            ) {
                Text("START")
            }

            Button(
                onClick = {
                    isRunning = false
                }
            ) {
                Text("PAUSE")
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = {
                isRunning = false
                seconds = 60
            }
        ) {
            Text("RESET")
        }
    }
}