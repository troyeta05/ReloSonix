package com.tzilacatzin.relosonix.ui

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ReloSonixClock() {
    var currentTime by remember { mutableStateOf(getFormattedTime()) }

    LaunchedEffect(Unit) {
        while (true) {
            currentTime = getFormattedTime()
            delay(1000)
        }
    }

    Text(
        text = currentTime,
        fontSize = 60.sp,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.headlineLarge
    )
}

fun getFormattedTime(): String {
    val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return sdf.format(Date())
}
