package com.tzilacatzin.relosonix

import BotonReproducir
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tzilacatzin.relosonix.ui.ReloSonixClock
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UIPrincipal()
        }
    }
}

@Composable
fun UIPrincipal() {
    val listaGenerico = listOf(R.raw.audiogenerico)

    val listaHoras = listOf(
        R.raw.la1, R.raw.las2, R.raw.las3, R.raw.las4, R.raw.las5, R.raw.las6,
        R.raw.las7, R.raw.las8, R.raw.las9, R.raw.las10, R.raw.las11, R.raw.las12
    )

    val listaMin = listOf(
        R.raw.con00, R.raw.con01, R.raw.con02, R.raw.con03, R.raw.con04, R.raw.con05,
        R.raw.con06, R.raw.con07, R.raw.con08, R.raw.con09, R.raw.con10, R.raw.con11,
        R.raw.con12, R.raw.con13, R.raw.con14, R.raw.con15, R.raw.con16, R.raw.con17,
        R.raw.con18, R.raw.con19, R.raw.con20, R.raw.con21, R.raw.con22, R.raw.con23,
        R.raw.con24, R.raw.con25, R.raw.con26, R.raw.con27, R.raw.con28, R.raw.con29,
        R.raw.con30, R.raw.con31, R.raw.con32, R.raw.con33, R.raw.con34, R.raw.con35,
        R.raw.con36, R.raw.con37, R.raw.con38, R.raw.con39, R.raw.con40, R.raw.con41,
        R.raw.con42, R.raw.con43, R.raw.con44, R.raw.con45, R.raw.con46, R.raw.con47,
        R.raw.con48, R.raw.con49, R.raw.con50, R.raw.con51, R.raw.con52, R.raw.con53,
        R.raw.con54, R.raw.con55, R.raw.con56, R.raw.con57, R.raw.con58, R.raw.con59
    )

    val listaParteDia = mapOf(
        "mañana" to R.raw.dia,
        "tarde" to R.raw.tarde,
        "noche" to R.raw.noche,
        "madrugada" to R.raw.madrugada
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(32.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ReloSonixClock()
            BotonReproducir(
                listaGenerico = listaGenerico,
                listaHoras = listaHoras,
                listaMin = listaMin,
                listaParteDia = listaParteDia
            )
        }
    }
}

fun getParteDelDia(): String {
    val horaActual = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return when (horaActual) {
        in 6..11 -> "mañana"
        in 12..17 -> "tarde"
        in 18..23 -> "noche"
        else -> "madrugada"
    }
}

fun getHoraYMinuto(): Pair<Int, Int> {
    val sdf = SimpleDateFormat("hh:mm", Locale.getDefault())
    val time = sdf.format(Date())
    val parts = time.split(":")
    val hour = parts[0].toInt()
    val minute = parts[1].toInt()
    return Pair(hour, minute)
}

fun reproducirAudiosEnOrden(context: Context, lista: List<Int>, index: Int = 0) {
    if (index >= lista.size) return

    val mediaPlayer = MediaPlayer.create(context, lista[index])
    mediaPlayer.setOnCompletionListener {
        mediaPlayer.release()
        reproducirAudiosEnOrden(context, lista, index + 1)
    }
    mediaPlayer.start()
}

@Preview(showBackground = true)
@Composable
fun UIPrincipalPreview() {
    UIPrincipal()
}