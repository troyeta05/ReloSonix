package com.tzilacatzin.relosonix.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tzilacatzin.relosonix.*

@Composable
fun BotonReproducir(
    listaGenerico: List<Int>,
    listaHoras: List<Int>,
    listaMin: List<Int>,
    listaParteDia: Map<String, Int>
) {
    val context = LocalContext.current

    Button(
        onClick = {
            val (hora, minuto) = getHoraYMinuto()
            val parteDita = getParteDelDia()

            val secuencia = listOf(
                listaGenerico[0],
                listaHoras[hora - 1],
                listaMin[minuto],
                listaParteDia[parteDita] ?: return@Button
            )

            reproducirAudiosEnOrden(context, secuencia)
        },
        modifier = Modifier
            .border(width = 2.dp, color = Color.White)
            .padding(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White
        )
    ) {
        Text(
            "▶️ ¿Qué hora es?",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }
}