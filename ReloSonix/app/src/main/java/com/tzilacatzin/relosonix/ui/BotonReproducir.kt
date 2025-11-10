import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import com.tzilacatzin.relosonix.*

@Composable
fun BotonReproducir(
    listaGenerico: List<Int>,
    listaHoras: List<Int>,
    listaMin: List<Int>,
    listaParteDia: Map<String, Int>
) {
    val context = LocalContext.current
    val shape = RoundedCornerShape(16.dp)

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
            .clip(shape)
            .border(width = 4.dp, color = Color.White, shape = shape)
            .padding(4.dp),
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White
        )
    ) {
        Text(
            "▶️ ¿Qué hora es?",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}